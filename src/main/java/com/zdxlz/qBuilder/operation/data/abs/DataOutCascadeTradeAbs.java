package com.zdxlz.qBuilder.operation.data.abs;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import com.zdxlz.qBuilder.common.core.domain.cascade.CascadePlatform;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeTry;
import com.zdxlz.qBuilder.common.core.exception.ServiceException;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.utils.SimpleThreadPool;
import com.zdxlz.qBuilder.common.core.utils.SpringUtils;
import com.zdxlz.qBuilder.common.core.utils.StringUtils;
import com.zdxlz.qBuilder.common.core.utils.http.HttpClientUtils;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.constant.Constants;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.dto.PublicInfoReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.operation.data.enums.CompensateStatusEnum;
import com.zdxlz.qBuilder.operation.data.enums.PlatfromTypeEnum;
import com.zdxlz.qBuilder.operation.service.CascadeTradeInfoService;
import com.zdxlz.qBuilder.operation.service.CascadeTradeTryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
public abstract class DataOutCascadeTradeAbs extends DataOutInterfaceForCascade {

    public CascadeReq fetchTrade(Object data) {
        this.init();
        if (BizModeEnum.ISSUED.getCode().equals(this.bizMode) && !PlatfromTypeEnum.FIRST_P.getCode().equals(this.platformType)) {
            //非一级平台不可下发数据
            return null;
        }
        if (BizModeEnum.REPORT.getCode().equals(this.bizMode) && !PlatfromTypeEnum.SECOND_P.getCode().equals(this.platformType)) {
            //非二级平台不可上报数据
            return null;
        }
        CascadeReq cascadeReq = new CascadeReq();
        PublicInfoReq publicInfoReq = new PublicInfoReq();
        cascadeReq.setPublicInfo(publicInfoReq);
        cascadeReq.setData(data);
        publicInfoReq.setBizMode(this.bizMode);
        publicInfoReq.setBizType(this.bizType);
        publicInfoReq.setRequestTime(DateUtil.formatDateTime(new Date()));
        publicInfoReq.setPlatformCode(this.platformCode);
        return cascadeReq;
    }

    public void execTrade(CascadeReq cascadeReq, String... platformCodes) {
        if (ObjectUtil.isNull(cascadeReq)) {
            return;
        }
        String reqJson = ObjectMapperUtil.beanToJson(cascadeReq);
        log.info("交易入参:{}",reqJson);
        Map<String, String> headers = Maps.newHashMap();
        headers.put("platformCode", this.platformCode);
        //根据platformCodes获取平台,指定平台下发
        List<String> platformList = new ArrayList<>();
        if (ObjectUtil.isNotNull(platformCodes)) {
            platformList.addAll(Arrays.asList(platformCodes));
        }
        List<CascadePlatform> cascadePlatformList = this.list(platformList);
        if (CollectionUtils.isEmpty(cascadePlatformList)) {
            return;
        }
        for (CascadePlatform platform : cascadePlatformList) {
            String url = String.format(this.dataOutUrl, platform.getIp(), platform.getPort());
            CascadeTradeInfo cascadeTradeInfo = this.fetchTradeInfo(url, "01", reqJson, platform.getPlatformCode());
            String tradeStatus = "";
            String result = "";
            String errorMsg = "";
            try {
                headers.put(Constants.TOKEN_PREFIX, this.getToken(platform.getIp(), platform.getPort(), this.platformCode,platform.getPlatformCode()));
                result = HttpClientUtils.doPost(url, reqJson, headers);
                log.info("{}返回结果:{}", url, result);
                tradeStatus = "1";
                cascadeTradeInfo.setCompensateStatus(CompensateStatusEnum.NULL.getCode());
                this.updateTradeInfo(cascadeTradeInfo, tradeStatus, cascadeTradeInfo.getCompensateStatus(), result, errorMsg);
            } catch (Exception e) {
                //保存交易异常记录
                tradeStatus = "2";
                errorMsg = ExceptionUtil.stacktraceToString(e);
                this.updateTradeInfo(cascadeTradeInfo, tradeStatus, CompensateStatusEnum.WAIT.getCode(), result, errorMsg);
                this.tryAgain(cascadeTradeInfo, this.tryAgainCount, this.tryAgainTime, headers);
            }
        }
    }

    private String getToken (String ip, String port, String reqPlatformCode, String resPlatformCode) {
        // 构造存放 Redis 的 key
        String redisKey = String.format("%s_%s_%s_%s", Constants.TOKEN_OUT, Constants.TOKEN_PREFIX, reqPlatformCode, resPlatformCode);
        // 尝试从 Redis 中获取 accessToken
        String accessToken = this.redisService.getCacheObject(redisKey);

        if (accessToken != null) {
            return accessToken;
        }

        // 若 Redis 中没有，构造 token 请求 URL
        String tokenUrl = String.format(this.tokenUrl, ip, port, this.platformCode);
        try {
            // 发送 GET 请求获取 token
            String result = HttpUtil.get(tokenUrl);
            if (StringUtils.isBlank(result)) {
                throw new ServiceException("获取accessToken令牌失败");
            }

            // 将返回结果转换为 AjaxResult 对象
            AjaxResult ajaxResult = ObjectMapperUtil.jsonToBean(result, AjaxResult.class);
            if (ajaxResult == null || !ajaxResult.isSuccess()) {
                String errorMsg = ajaxResult != null ? String.valueOf(ajaxResult.get(AjaxResult.MSG_TAG)) : "获取accessToken令牌失败";
                throw new ServiceException(errorMsg);
            }

            // 安全转换并获取 data 中的 accessToken
            Object dataObj = ajaxResult.get("data");
            if (dataObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, String> data = (Map<String, String>) dataObj;
                accessToken = data.get(Constants.TOKEN_PREFIX);
            }

            if (accessToken == null) {
                throw new ServiceException("获取accessToken令牌失败");
            }

            // 将 accessToken 存入 Redis
            this.redisService.setCacheObject(redisKey, accessToken, 3600L, TimeUnit.SECONDS);
            log.info("accessToken:{}", accessToken);
        } catch (Exception e) {
            throw new ServiceException("获取accessToken令牌失败"+e.getMessage());
        }

        return accessToken;
    }

    private CascadeTradeInfo fetchTradeInfo(String url, String tradeType, String reqBody, String platformCode) {
        CascadeTradeInfo cascadeTradeInfo = new CascadeTradeInfo();
        cascadeTradeInfo.setTradeInterface(BizTypeEnum.getDesc(this.bizType));
        cascadeTradeInfo.setInterfaceAddress(url);
        cascadeTradeInfo.setTradeType(tradeType);
        cascadeTradeInfo.setReqTime(LocalDateTime.now());
        cascadeTradeInfo.setReqBody(reqBody);
        cascadeTradeInfo.setResPlatformCode(platformCode);
        this.saveTradeInfo(cascadeTradeInfo);
        return cascadeTradeInfo;
    }

    private void updateTradeInfo(CascadeTradeInfo cascadeTradeInfo, String tradeStatus, String compensateStatus, String resBody, String errorMsg) {
        cascadeTradeInfo.setTradeStatus(tradeStatus);
        cascadeTradeInfo.setCompensateStatus(compensateStatus);
        cascadeTradeInfo.setResBody(resBody);
        cascadeTradeInfo.setResTime(LocalDateTime.now());
        cascadeTradeInfo.setErrorMsg(errorMsg);
        this.updateTradeInfo(cascadeTradeInfo);
    }

    private void tryAgain(CascadeTradeInfo cascadeTradeInfo, Integer tryCount, Integer tryTime, Map<String, String> headers) {
        SimpleThreadPool.instance().run(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tryCount; i++) {
                    try {
                        Thread.sleep(tryTime * 1000);
                    } catch (InterruptedException e) {
                        log.error("线程休眠失败", e);
                    }
                    CascadeTradeTry cascadeTradeTry = new CascadeTradeTry();
                    cascadeTradeTry.setTradeId(cascadeTradeInfo.getTradeId());
                    cascadeTradeTry.setReqTime(LocalDateTime.now());
                    cascadeTradeTry.setTryNum(i);
                    try {
                        String resBody = HttpClientUtils.doPost(cascadeTradeInfo.getInterfaceAddress(), cascadeTradeInfo.getReqBody(), headers);
                        cascadeTradeTry.setResBody(resBody);
                        cascadeTradeInfo.setCompensateStatus(CompensateStatusEnum.NULL.getCode());
                        break;
                    } catch (Exception e) {
                        log.error(cascadeTradeInfo.getResPlatformCode() + cascadeTradeInfo.getTradeInterface() + "请求失败:", e);
                        cascadeTradeTry.setErrorMsg(ExceptionUtil.stacktraceToString(e));
                    } finally {
                        CascadeTradeTryService cascadeTradeTryService = SpringUtils.getBean(CascadeTradeTryService.class);
                        cascadeTradeTryService.save(cascadeTradeTry);
                    }
                }
                if (StringUtils.isBlank(cascadeTradeInfo.getCompensateStatus())) {
                    cascadeTradeInfo.setCompensateStatus(CompensateStatusEnum.WAIT.getCode());
                }
                CascadeTradeInfoService cascadeTradeInfoService = SpringUtils.getBean(CascadeTradeInfoService.class);
                cascadeTradeInfoService.update(cascadeTradeInfo);
            }
        });
    }

    protected void send(Object t, BusinessType operationType, String... platformCodes) {
        Map<String, Object> params = new HashMap<>();
        params.put("operationType", operationType.name());
        params.put("data", t);
        CascadeReq cascadeReq = this.fetchTrade(params);
        this.execTrade(cascadeReq,platformCodes);
    }

}

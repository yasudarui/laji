package com.zdxlz.qBuilder.operation.service.impl;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.zdxlz.qBuilder.common.core.constant.Constants;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.AbandonCompensateDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.CompensateCascadeTradeDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeCompensate;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.utils.SecurityUtils;
import com.zdxlz.qBuilder.common.core.utils.http.HttpClientUtils;
import com.zdxlz.qBuilder.operation.data.enums.CompensateStatusEnum;
import com.zdxlz.qBuilder.operation.data.enums.TradeTypeEnum;
import com.zdxlz.qBuilder.operation.data.util.GetTokenUtil;
import com.zdxlz.qBuilder.operation.mapper.CascadeTradeCompensateMapper;
import com.zdxlz.qBuilder.operation.mapper.CascadeTradeInfoMapper;
import com.zdxlz.qBuilder.operation.service.CascadeTradeCompensateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @author zhangtao
 * @date 2024-10-22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CascadeTradeCompensateServiceImpl extends ServiceImpl<CascadeTradeCompensateMapper, CascadeTradeCompensate> implements CascadeTradeCompensateService {

    public final String PATH = "/api/v1";

    private final CascadeTradeInfoMapper cascadeTradeInfoMapper;

    @Override
    public List<CascadeTradeCompensate> listCascadeTradeCompensates(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto) {
        return this.list(Wrappers.<CascadeTradeCompensate>lambdaQuery()
                .eq(CascadeTradeCompensate::getTradeId, queryPageCascadeTradeTryDto.getTradeId())
                .eq(CascadeTradeCompensate::getDelFlag, Constants.DEL_FLAG_ACTIVE).orderByDesc(CascadeTradeCompensate::getCreateTime));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean compensate(CompensateCascadeTradeDto compensateCascadeTradeDto) {
        CascadeTradeInfo cascadeTradeInfo = cascadeTradeInfoMapper.selectById(compensateCascadeTradeDto.getTradeId());
        if (Objects.nonNull(cascadeTradeInfo)) {
            if (!(Objects.equals(CompensateStatusEnum.WAIT.getCode(), cascadeTradeInfo.getCompensateStatus())
                    || Objects.equals(CompensateStatusEnum.FAIL.getCode(), cascadeTradeInfo.getCompensateStatus()))) {
                return Boolean.FALSE;
            }
            //补偿
            String reqJson = compensateCascadeTradeDto.getReqBody();
            Map<String, String> headers = Maps.newHashMap();
            String reqPlatformCode = cascadeTradeInfo.getReqPlatformCode();
            String resPlatformCode = cascadeTradeInfo.getResPlatformCode();
            String interfaceAddress = cascadeTradeInfo.getInterfaceAddress();
            CascadeTradeCompensate cascadeTradeCompensate = new CascadeTradeCompensate();
            cascadeTradeCompensate.setTradeId(compensateCascadeTradeDto.getTradeId());
            cascadeTradeCompensate.setInterfaceAddress(compensateCascadeTradeDto.getInterfaceAddress());
            cascadeTradeCompensate.setTradeType(TradeTypeEnum.MANUAL.getCode());
            cascadeTradeCompensate.setCreateBy(SecurityUtils.getUsername());
            cascadeTradeCompensate.setCreateTime(new Date());
            cascadeTradeCompensate.setDelFlag(Constants.DEL_FLAG_ACTIVE);

            cascadeTradeCompensate.setReqTime(LocalDateTime.now());
            cascadeTradeCompensate.setReqBody(compensateCascadeTradeDto.getReqBody());
            cascadeTradeCompensate.setCompensateStatus(CompensateStatusEnum.WAIT.getCode());
            this.save(cascadeTradeCompensate);
            int length = interfaceAddress.lastIndexOf(PATH) + PATH.length();
            String tokenUrl = interfaceAddress.substring(0, length) + "/auth/token/" + reqPlatformCode;
            log.info("tokenUrl:{}", tokenUrl);
            headers.put("platformCode", reqPlatformCode);
            String compensateStatus = "";
            String result = "";
            String errorMsg = "";
            try {
                String token = GetTokenUtil.getToken(reqPlatformCode, resPlatformCode, tokenUrl);
                headers.put("token",token);
                result = HttpClientUtils.doPost(interfaceAddress, reqJson, headers);
                log.info("{}返回结果:{}", interfaceAddress, result);
                compensateStatus = CompensateStatusEnum.SUCCESS.getCode();
                return Boolean.TRUE;
            } catch (Exception e) {
                log.error("{}{}请求失败:", resPlatformCode, compensateCascadeTradeDto.getInterfaceAddress(), e);
                //保存交易异常记录
                errorMsg = ExceptionUtil.stacktraceToString(e);
                compensateStatus = CompensateStatusEnum.FAIL.getCode();
            } finally {
                cascadeTradeCompensate.setResBody(result);
                cascadeTradeCompensate.setResTime(LocalDateTime.now());
                cascadeTradeCompensate.setCompensateStatus(compensateStatus);
                cascadeTradeCompensate.setErrorMsg(errorMsg);
                cascadeTradeCompensate.setUpdateTime(new Date());
                cascadeTradeCompensate.setUpdateBy(SecurityUtils.getUsername());
                this.updateById(cascadeTradeCompensate);

                cascadeTradeInfo.setCompensateStatus(compensateStatus);
                cascadeTradeCompensate.setTradeType(TradeTypeEnum.MANUAL.getCode());
                cascadeTradeInfo.setCompensateNum(Objects.nonNull(cascadeTradeInfo.getCompensateNum())? cascadeTradeInfo.getCompensateNum() + 1 : 1);
                cascadeTradeInfo.setUpdateTime(new Date());
                cascadeTradeInfo.setUpdateBy(SecurityUtils.getUsername());
                cascadeTradeInfoMapper.updateById(cascadeTradeInfo);
            }
        }
        return Boolean.FALSE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean abandonCompensate(AbandonCompensateDto abandonCompensateDto) {
        CascadeTradeInfo cascadeTradeInfo = cascadeTradeInfoMapper.selectById(abandonCompensateDto.getTradeId());
        if (Objects.nonNull(cascadeTradeInfo)) {
            if (!(Objects.equals(CompensateStatusEnum.WAIT.getCode(), cascadeTradeInfo.getCompensateStatus())
                    || Objects.equals(CompensateStatusEnum.FAIL.getCode(), cascadeTradeInfo.getCompensateStatus()))) {
                return Boolean.FALSE;
            }
            cascadeTradeInfo.setCompensateStatus(CompensateStatusEnum.NULL.getCode());
            cascadeTradeInfo.setUpdateBy(SecurityUtils.getUsername());
            cascadeTradeInfo.setUpdateTime(new Date());
            cascadeTradeInfoMapper.updateById(cascadeTradeInfo);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

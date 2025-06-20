package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zdxlz.qBuilder.api.report.RemoteReportWarnOutService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.report.entity.ReportWarnCount;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表统计-告警统计-上报
 * @Date: 2024/10/28 16:32
 * @author caomin
 */
@Slf4j
@Service
public class ReportWarnService extends DataInCascadeTradeAbs {
    @Autowired
    private RemoteReportWarnOutService remoteReportWarnOutService;

    public ReportWarnService() {
        this.tradeType = BizTypeEnum.DEVICE_REPORT.getDesc() ;
        this.tradeMode = BizModeEnum.REPORT.getCode() ;
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        JSONArray jsonObject = JSONArray.parseArray(JSONObject.toJSONString(data));
        List<ReportWarnCount>  warnCountDtoList = jsonObject.toJavaList(ReportWarnCount.class);
        AjaxResult result = this.remoteReportWarnOutService.reportWarnCountOut(warnCountDtoList, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

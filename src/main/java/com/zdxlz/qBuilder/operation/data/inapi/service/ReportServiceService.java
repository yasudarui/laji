package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zdxlz.qBuilder.api.report.RemoteReportServiceOutService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.report.entity.ReportServiceCount;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表统计-服务统计-上报
 * @Date: 2024/10/28 16:32
 * @author caomin
 */
@Service
public class ReportServiceService extends DataInCascadeTradeAbs {
    @Autowired
    private RemoteReportServiceOutService remoteReportServiceOutService;

    public ReportServiceService() {
        this.tradeType = BizTypeEnum.SERVICE_REPORT.getDesc() ;
        this.tradeMode = BizModeEnum.REPORT.getCode() ;
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        JSONArray jsonObject = JSONArray.parseArray(JSONObject.toJSONString(data));
        List<ReportServiceCount>  deviceCountDtoList = jsonObject.toJavaList(ReportServiceCount.class);
        AjaxResult result = this.remoteReportServiceOutService.reportServiceCountOut(deviceCountDtoList, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

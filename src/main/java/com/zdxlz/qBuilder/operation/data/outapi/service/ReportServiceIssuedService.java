package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportServiceCountDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表统计-服务统计-上报
 * @Date: 2024/10/28 16:32
 * @author caomin
 */
@Service
public class ReportServiceIssuedService extends DataOutCascadeTradeAbs {

    public ReportServiceIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.SERVICE_REPORT.getCode();
    }

    public void reportServiceCountOut(List<ReportServiceCountDto> serviceCountList){
        CascadeReq cascadeReq = this.fetchTrade(serviceCountList);
        this.execTrade(cascadeReq);
    }

}

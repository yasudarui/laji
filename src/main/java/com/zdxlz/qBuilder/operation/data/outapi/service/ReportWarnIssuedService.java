package com.zdxlz.qBuilder.operation.data.outapi.service;


import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportWarnCountDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表统计-告警统计-上报
 * @Date: 2024/10/28 16:32
 * @author caomin
 */
@Service
public class ReportWarnIssuedService extends DataOutCascadeTradeAbs {

    public ReportWarnIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.ALARM_REPORT.getCode();
    }

    public void reportWarnCountOut(List<ReportWarnCountDto> warnCountList){
        CascadeReq cascadeReq = this.fetchTrade(warnCountList);
        this.execTrade(cascadeReq);
    }

}

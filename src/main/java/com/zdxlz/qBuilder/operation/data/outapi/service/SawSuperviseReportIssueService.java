package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.situation.awareness.entity.SawSuperviseReport;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SawSuperviseReportIssueService  extends DataOutCascadeTradeAbs {


    public SawSuperviseReportIssueService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.SAW_SUPERVISE_REPORT.getCode();
    }


    public void getSawSuperviseReport(List<SawSuperviseReport> sawSuperviseReportList) {
       super.send(sawSuperviseReportList, BusinessType.INSERT);
    }
}

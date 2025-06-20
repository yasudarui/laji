package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.SaveCryYearListReportedDto;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.UpdateCryYearListDto;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SecretApplicationYearTaskReportService extends DataOutCascadeTradeAbs {
    public SecretApplicationYearTaskReportService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.CRY_YEAR_TASK.getCode();
    }

    public void reportAddSecretAppYearTask(SaveCryYearListReportedDto reportedDto){
        super.send(reportedDto, BusinessType.INSERT);
    }

    public void reportUpdateSecretAppYearTask(UpdateCryYearListDto updateCryYearListDto){
        super.send(updateCryYearListDto, BusinessType.UPDATE);
    }
}
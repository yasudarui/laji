package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.compliance.check.entity.FrontEndProcessor;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontEndProcessorIssuedService extends DataOutCascadeTradeAbs {
    public FrontEndProcessorIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode();
        this.bizType = BizTypeEnum.FrontEndProcessor_REPORT.getCode();
    }

    public void saveFrontEndProcessor(FrontEndProcessor frontEndProcessor) {
        super.send(frontEndProcessor, BusinessType.INSERT);
    }

    public void updateFrontEndProcessor(FrontEndProcessor frontEndProcessor) {
        super.send(frontEndProcessor, BusinessType.UPDATE);
    }

    public void deleteFrontEndProcessor(List<String> ids) {
        super.send(ids, BusinessType.DELETE);
    }
}

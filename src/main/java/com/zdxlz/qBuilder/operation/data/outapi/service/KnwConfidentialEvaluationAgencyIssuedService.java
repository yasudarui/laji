package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.DeleteEvaluationAgencyDto;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.SaveEvaluateAgencyDto;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1 * @Author: zhongjian.zhang
 * 2 * @Date: 2024/10/29 9:37 上午
 * 3
 */
@Service
public class KnwConfidentialEvaluationAgencyIssuedService extends DataOutCascadeTradeAbs {

    public KnwConfidentialEvaluationAgencyIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode();
        this.bizType = BizTypeEnum.KNW_EVALUATE_AGENCY_ISSUED.getCode();
    }

    public void deleteEvaluateAgencyById(DeleteEvaluationAgencyDto deleteEvaluationAgencyDto) {
        super.send(deleteEvaluationAgencyDto, BusinessType.DELETE);
    }

    public void updateEvaluateAgency(SaveEvaluateAgencyDto saveEvaluateAgencyDto) {
        super.send(saveEvaluateAgencyDto, BusinessType.UPDATE);
    }

    public void saveEvaluateAgency(SaveEvaluateAgencyDto saveEvaluateAgencyDto) {
        super.send(saveEvaluateAgencyDto, BusinessType.INSERT);
    }


    public void syncEvaluateAgency(Object data, List<String> platformCodes) {
        super.send(data, BusinessType.ASYNC,platformCodes.toArray(new String[0]));
    }
}

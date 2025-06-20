package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.SaveAppPwdIndexInfoDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SwitchImproveAndEvaluateIssuedService extends DataOutCascadeTradeAbs {

    public SwitchImproveAndEvaluateIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode() ;
        this.bizType = BizTypeEnum.CRY_APP_DECLARE_ISSUED.getCode();
    }

    public void issuedSwitchImproveAndEvaluate(SaveAppPwdIndexInfoDto saveAppPwdIndexInfoDto){
        CascadeReq cascadeReq = this.fetchTrade(saveAppPwdIndexInfoDto);
        this.execTrade(cascadeReq);

    }
}
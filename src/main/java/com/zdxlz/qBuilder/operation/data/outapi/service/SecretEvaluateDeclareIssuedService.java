package com.zdxlz.qBuilder.operation.data.outapi.service;

import cn.hutool.core.util.ObjectUtil;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.InsertCryEvaluateDeclareBaseInfoDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SecretEvaluateDeclareIssuedService extends DataOutCascadeTradeAbs {

    public SecretEvaluateDeclareIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.CRY_EVALUATE_DECLARE_REPORT.getCode();
    }

    public void issuedSecretEvaluate(InsertCryEvaluateDeclareBaseInfoDto insertCryEvaluateDeclareBaseInfoDto){
        if (ObjectUtil.isNull(insertCryEvaluateDeclareBaseInfoDto)) {
            return;
        }
        CascadeReq cascadeReq = this.fetchTrade(insertCryEvaluateDeclareBaseInfoDto);
        this.execTrade(cascadeReq);
    }
}
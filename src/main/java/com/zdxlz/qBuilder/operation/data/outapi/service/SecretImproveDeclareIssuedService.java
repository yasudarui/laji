package com.zdxlz.qBuilder.operation.data.outapi.service;

import cn.hutool.core.util.ObjectUtil;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.InsertCryImproveDeclareDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SecretImproveDeclareIssuedService extends DataOutCascadeTradeAbs {

    public SecretImproveDeclareIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.CRY_IMPROVE_DECLARE_REPORT.getCode();
    }

    public void issuedSecretImprove(InsertCryImproveDeclareDto insertCryImproveDeclareDto){
        if (ObjectUtil.isNull(insertCryImproveDeclareDto)) {
            return;
        }
        CascadeReq cascadeReq = this.fetchTrade(insertCryImproveDeclareDto);
        this.execTrade(cascadeReq);
    }
}
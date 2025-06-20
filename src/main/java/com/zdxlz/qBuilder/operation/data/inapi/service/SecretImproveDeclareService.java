package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.cryptography.ledger.RemoteSecretAppService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.InsertCryImproveAndEvaluateDeclareDto;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.InsertCryImproveDeclareDto;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretImproveDeclareService extends DataInCascadeTradeAbs {
    @Autowired
    private RemoteSecretAppService remoteSecretAppService ;

    public SecretImproveDeclareService() {
        this.tradeType = BizTypeEnum.CRY_IMPROVE_DECLARE_REPORT.getDesc() ;
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }
    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        InsertCryImproveDeclareDto insertCryImproveDeclareDto = ObjectMapperUtil.objToBean(data, InsertCryImproveDeclareDto.class);
        InsertCryImproveAndEvaluateDeclareDto insertCryImproveAndEvaluateDeclareDto = new InsertCryImproveAndEvaluateDeclareDto();
        insertCryImproveAndEvaluateDeclareDto.setInsertCryImproveDeclareDto(insertCryImproveDeclareDto);
        AjaxResult result = this.remoteSecretAppService.improveAndEvaluateDeclare(insertCryImproveAndEvaluateDeclareDto, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}
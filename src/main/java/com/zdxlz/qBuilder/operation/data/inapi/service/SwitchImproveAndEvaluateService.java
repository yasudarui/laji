package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.cryptography.ledger.RemoteSecretAppService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.SaveAppPwdIndexInfoDto;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwitchImproveAndEvaluateService extends DataInCascadeTradeAbs {
    @Autowired
    private RemoteSecretAppService remoteSecretAppService ;

    public SwitchImproveAndEvaluateService() {
        this.tradeType = BizTypeEnum.CRY_APP_DECLARE_ISSUED.getDesc() ;
        this.tradeMode = BizModeEnum.ISSUED.getCode();
    }
    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        SaveAppPwdIndexInfoDto saveAppPwdIndexInfoDto = ObjectMapperUtil.objToBean(data, SaveAppPwdIndexInfoDto.class);
        AjaxResult result = this.remoteSecretAppService.switchImproveAndEvaluate(saveAppPwdIndexInfoDto, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}
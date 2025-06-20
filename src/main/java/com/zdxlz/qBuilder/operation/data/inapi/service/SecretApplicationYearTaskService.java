package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.cryptography.ledger.RemoteSecretAppService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.SaveCryYearListReportedDto;
import com.zdxlz.qBuilder.common.core.domain.cryptography.ledger.dto.applicationManagement.UpdateCryYearListDto;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecretApplicationYearTaskService extends DataInCascadeTradeAbs {
    @Autowired
    private RemoteSecretAppService remoteSecretAppService ;

    public SecretApplicationYearTaskService() {
        this.tradeType = BizTypeEnum.CRY_YEAR_TASK.getDesc() ;
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }
    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        AjaxResult result = null;
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        if (BusinessType.INSERT.name().equals(operationType)) {
            SaveCryYearListReportedDto reportedDto = ObjectMapperUtil.objToBean(data, SaveCryYearListReportedDto.class);
            result = this.remoteSecretAppService.addSecretApplicationYearTask(reportedDto, SecurityConstants.INNER);
        } else if (BusinessType.UPDATE.name().equals(operationType)) {
            UpdateCryYearListDto updateCryYearListDto = ObjectMapperUtil.objToBean(data, UpdateCryYearListDto.class);
            result = this.remoteSecretAppService.updateSecretApplicationYearTask(updateCryYearListDto, SecurityConstants.INNER);
        } else {
            return ObjectMapperUtil.beanToJson(AjaxResult.error("operationType is not supported"));
        }
        return ObjectMapperUtil.beanToJson(result);
    }
}
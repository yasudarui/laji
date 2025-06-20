package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.DeleteAmgAppConfigDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.SaveAmgAppConfigDto;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AmgAppConfigIssuedService  extends DataOutCascadeTradeAbs {

    public AmgAppConfigIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.APP_CONFIG_REPORT.getCode();
    }

    public void saveConfigAmgApp(List<SaveAmgAppConfigDto> saveAmgAppConfigDtos) {
        super.send(saveAmgAppConfigDtos, BusinessType.INSERT);
    }

    public void deleteConfigAmgApp(DeleteAmgAppConfigDto deleteAmgAppConfigDto) {
        super.send(deleteAmgAppConfigDto, BusinessType.DELETE);
    }
}

package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgSystem;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AmgSystemIssuedService extends DataOutCascadeTradeAbs {

    public AmgSystemIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.SYSTEM_ASSET_REPORT.getCode();
    }

    public void saveAmgSystem(AmgSystem amgSystem){
        super.send(amgSystem, BusinessType.INSERT);
    }

    public void updateAmgSystem(AmgSystem amgSystem){
        super.send(amgSystem, BusinessType.UPDATE);
    }

    public void deleteAmgSystem(List<String> ids){
        super.send(ids, BusinessType.DELETE);
    }

    public void importAmgSystem(List<AmgSystem> amgSystemList){
        super.send(amgSystemList, BusinessType.IMPORT);
    }
}

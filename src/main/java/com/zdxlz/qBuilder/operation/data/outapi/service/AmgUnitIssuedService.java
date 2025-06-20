package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgUnit;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AmgUnitIssuedService extends DataOutCascadeTradeAbs {

    public AmgUnitIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode() ;
        this.bizType = BizTypeEnum.UNIT_ISSUED.getCode();
    }

    public void saveAmgUnit(AmgUnit unit){
       super.send(unit, BusinessType.INSERT);
    }

    public void updateAmgUnit(AmgUnit unit) {
        super.send(unit, BusinessType.UPDATE);
    }

    public void removeAmgUnit(AmgUnit amgUnit) {
        super.send(amgUnit.getUnitId(), BusinessType.DELETE);
    }
}

package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDictType;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SysDictTypeIssuedService extends DataOutCascadeTradeAbs {


    public SysDictTypeIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode();
        this.bizType = BizTypeEnum.DICT_TYPE_ISSUED.getCode();
    }

    public void addSysDictType(SysDictType sysDictType) {
        super.send(sysDictType, BusinessType.INSERT);
    }

    public void editSysDictType(SysDictType sysDictType) {
        super.send(sysDictType, BusinessType.UPDATE);
    }

    public void removeSysDictType(Long[] dictIds) {
        super.send(dictIds, BusinessType.DELETE);
    }
}

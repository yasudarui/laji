package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDictData;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SysDictDataIssuedService extends DataOutCascadeTradeAbs {


    public SysDictDataIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode();
        this.bizType = BizTypeEnum.DICT_DATA_ISSUED.getCode();
    }

    public void addSysDictData(SysDictData sysDictData) {
        super.send(sysDictData, BusinessType.INSERT);
    }

    public void editSysDictData(SysDictData sysDictData) {
        super.send(sysDictData, BusinessType.UPDATE);
    }

    public void removeSysDictData(Long[] dictDataId) {
        super.send(dictDataId, BusinessType.DELETE);
    }
}

package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.system.entity.SysDept;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;


@Service
public class SysDeptIssuedService extends DataOutCascadeTradeAbs {

    public SysDeptIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode() ;
        this.bizType = BizTypeEnum.DEPT_ISSUED.getCode();
    }


    public void addSysDept(SysDept sysDept) {
        super.send(sysDept, BusinessType.INSERT);
    }


    public void removeSysDept(Long deptId) {
       super.send(deptId, BusinessType.DELETE);

    }

    public void editSysDept(SysDept sysDept) {
        super.send(sysDept, BusinessType.UPDATE);
    }
}

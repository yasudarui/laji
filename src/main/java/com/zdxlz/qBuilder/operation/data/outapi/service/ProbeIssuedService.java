package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.compliance.check.entity.Probe;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbeIssuedService extends DataOutCascadeTradeAbs {


    public ProbeIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode();
        this.bizType = BizTypeEnum.PROBE_REPORT.getCode();
    }

    public void saveProbe(Probe probe) {
        super.send(probe, BusinessType.INSERT);
    }

    public void updateProbe(Probe probe) {
        super.send(probe, BusinessType.UPDATE);
    }

    public void deleteProbe(List<String> ids) {
        super.send(ids, BusinessType.DELETE);
    }
}

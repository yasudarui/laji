package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgDevice;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AmgDeviceIssuedService extends DataOutCascadeTradeAbs {

    public AmgDeviceIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.DEVICE_ASSET_REPORT.getCode();
    }

    public void saveAmgDevice(AmgDevice amgDevice) {
        super.send(amgDevice, BusinessType.INSERT);
    }

    public void updateAmgDevice(AmgDevice amgDevice) {
        super.send(amgDevice, BusinessType.UPDATE);
    }

    public void deleteAmgDevice(List<String> ids) {
        super.send(ids, BusinessType.DELETE);
    }

    public void importAmgDevice(List<AmgDevice> deviceList) {
        super.send(deviceList, BusinessType.IMPORT);
    }
}

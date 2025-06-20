package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.SaveAmgAppDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgApp;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AmgAppIssuedService extends DataOutCascadeTradeAbs {

    public AmgAppIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.APP_ASSET_REPORT.getCode();
    }

    public void saveAmgApp(SaveAmgAppDto saveAmgAppDto) {
        super.send(saveAmgAppDto, BusinessType.INSERT);
    }

    public void deleteAmgApp(List<String> ids) {
        super.send(ids, BusinessType.DELETE);
    }

    public void updateAmgApp(AmgApp app) {
        super.send(app, BusinessType.UPDATE);
    }

    public void importAmgApp(List<AmgApp> amgAppList) {
        super.send(amgAppList, BusinessType.IMPORT);
    }
}

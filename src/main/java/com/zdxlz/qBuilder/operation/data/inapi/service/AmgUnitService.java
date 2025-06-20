package com.zdxlz.qBuilder.operation.data.inapi.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.zdxlz.qBuilder.api.asset.RemoteAssetService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgUnit;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AmgUnitService extends DataInCascadeTradeAbs {


    private final RemoteAssetService remoteAssetService;

    public AmgUnitService(RemoteAssetService remoteAssetService) {
        this.remoteAssetService = remoteAssetService;
        this.tradeType = BizTypeEnum.UNIT_ISSUED.getDesc();
        this.tradeMode = BizModeEnum.ISSUED.getCode();
    }

    @Override
    public String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        if (BusinessType.INSERT.name().equals(operationType)) {
            AmgUnit amgUnit = MapUtil.get(map, "data", new TypeReference<AmgUnit>() {
            });
            return ObjectMapperUtil.beanToJson( remoteAssetService.saveAmgUnit(amgUnit, SecurityConstants.INNER));
        } else if (BusinessType.UPDATE.name().equals(operationType)) {
            AmgUnit amgUnit = MapUtil.get(map, "data", new TypeReference<AmgUnit>() {
            });
            return ObjectMapperUtil.beanToJson(remoteAssetService.updateAmgUnit(amgUnit, SecurityConstants.INNER));
        } else if (BusinessType.DELETE.name().equals(operationType)) {
            String unitId = MapUtil.getStr(map, "data");
            return ObjectMapperUtil.beanToJson( remoteAssetService.deleteUnitId(unitId, SecurityConstants.INNER));
        } else {
            return ObjectMapperUtil.beanToJson(AjaxResult.error("操作类型错误"));
        }
    }


}

package com.zdxlz.qBuilder.operation.data.inapi.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zdxlz.qBuilder.api.asset.RemoteAssetService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.asset.management.dto.SaveAmgAppDto;
import com.zdxlz.qBuilder.common.core.domain.asset.management.entity.AmgApp;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AmgAppService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteAssetService remoteAssetService;

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        AjaxResult result = null;
        if (BusinessType.INSERT.name().equals(operationType)) {
            SaveAmgAppDto saveAmgAppDto = MapUtil.get(map, "data", new TypeReference<SaveAmgAppDto>() {
            });
            saveAmgAppDto.setPlatformCode(platformCode);
            result = remoteAssetService.saveAmgApp(saveAmgAppDto, SecurityConstants.INNER);
        } else if (BusinessType.UPDATE.name().equals(operationType)) {
            AmgApp amgApp = MapUtil.get(map, "data", new TypeReference<AmgApp>() {
            });
            amgApp.setPlatformCode(platformCode);
            result = remoteAssetService.updateAmgApp(amgApp, SecurityConstants.INNER);
        } else if (BusinessType.DELETE.name().equals(operationType)) {
            JSONArray idsArr = JSONArray.parseArray(JSONObject.toJSONString(map.get("data")));
            List<String> ids = idsArr.toJavaList(String.class);
            result = remoteAssetService.deleteAmgApp(ids, SecurityConstants.INNER);
        } else if (BusinessType.IMPORT.name().equals(operationType)) {
            JSONArray appArray = JSONArray.parseArray(JSONObject.toJSONString(map.get("data")));
            for (int i = 0; i < appArray.size(); i++) {
                JSONObject deviceObj = appArray.getJSONObject(i);
                deviceObj.put("platformCode", platformCode);
            }
            result = remoteAssetService.importAmgApp(ObjectMapperUtil.beanToJson(appArray), SecurityConstants.INNER);
        } else {
            return ObjectMapperUtil.beanToJson(AjaxResult.error("operationType is not supported"));
        }
        return ObjectMapperUtil.beanToJson(result);
    }
}

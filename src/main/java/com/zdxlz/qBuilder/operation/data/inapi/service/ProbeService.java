package com.zdxlz.qBuilder.operation.data.inapi.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.zdxlz.qBuilder.api.compliance.check.RemoteProbeAndFrontEndProcessorService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.compliance.check.entity.Probe;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProbeService  extends DataInCascadeTradeAbs {

    private final RemoteProbeAndFrontEndProcessorService remoteProbeAndFrontEndProcessorService;

    public ProbeService(RemoteProbeAndFrontEndProcessorService remoteProbeAndFrontEndProcessorService) {
        this.remoteProbeAndFrontEndProcessorService = remoteProbeAndFrontEndProcessorService;
        this.tradeType = BizTypeEnum.PROBE_REPORT.getDesc();
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }


    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        if (BusinessType.INSERT.name().equals(operationType)) {
            Probe probe = MapUtil.get(map, "data", new TypeReference<Probe>() {
            });
            return ObjectMapperUtil.beanToJson( remoteProbeAndFrontEndProcessorService.saveProbe(probe, SecurityConstants.INNER));
        } else if (BusinessType.UPDATE.name().equals(operationType)) {
            Probe Probe = MapUtil.get(map, "data", new TypeReference<Probe>() {
            });
            return ObjectMapperUtil.beanToJson(remoteProbeAndFrontEndProcessorService.updateProbe(Probe, SecurityConstants.INNER));
        } else if (BusinessType.DELETE.name().equals(operationType)) {
            List<String> ids = MapUtil.get(map, "data", new TypeReference<List<String>>() {
            });
            return ObjectMapperUtil.beanToJson(remoteProbeAndFrontEndProcessorService.deleteProbe(ids, SecurityConstants.INNER));
        } else {
            return ObjectMapperUtil.beanToJson(AjaxResult.error("操作类型错误"));
        }
    }
}

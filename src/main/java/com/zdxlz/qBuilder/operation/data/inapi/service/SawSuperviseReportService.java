package com.zdxlz.qBuilder.operation.data.inapi.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.zdxlz.qBuilder.api.situation.awareness.RemoteSituationAwarenessService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.situation.awareness.entity.SawSuperviseReport;
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
public class SawSuperviseReportService extends DataInCascadeTradeAbs {

    private final RemoteSituationAwarenessService remoteSituationAwarenessService;

    public SawSuperviseReportService(RemoteSituationAwarenessService remoteSituationAwarenessService) {
        this.remoteSituationAwarenessService = remoteSituationAwarenessService;
        this.tradeType = BizTypeEnum.SAW_SUPERVISE_REPORT.getDesc();
        this.tradeMode = BizModeEnum.ISSUED.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        if (BusinessType.INSERT.name().equals(operationType)) {
            List<SawSuperviseReport> sawSuperviseReports = MapUtil.get(map, "data", new TypeReference<List<SawSuperviseReport>>() {
            });
            return ObjectMapperUtil.beanToJson( remoteSituationAwarenessService.asyncDataReport(sawSuperviseReports, SecurityConstants.INNER));
        } else {
            return ObjectMapperUtil.beanToJson(AjaxResult.error("operationType is not supported"));
        }
    }
}

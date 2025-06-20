package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.monitor.RemoteMonitorDataInService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonRuntimeData;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运行指标数据接入
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
public class MonRuntimeDataService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteMonitorDataInService remoteMonitorDataInService;

    public MonRuntimeDataService() {
        this.tradeType = BizTypeEnum.MON_RUNTIME_DATA_REPORT.getDesc();
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        MonRuntimeData runtimeData = ObjectMapperUtil.objToBean(data, MonRuntimeData.class);
        runtimeData.setPlatformCode(cascadeReq.getPublicInfo().getPlatformCode());
        AjaxResult result = this.remoteMonitorDataInService.saveRuntimeData(runtimeData, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

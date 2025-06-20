package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.monitor.RemoteMonitorDataInService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonServiceData;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务指标数据接入
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
public class MonServiceDataService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteMonitorDataInService remoteMonitorDataInService;

    public MonServiceDataService() {
        this.tradeType = BizTypeEnum.MON_SERVICE_DATA_REPORT.getDesc();
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        MonServiceData serviceData = ObjectMapperUtil.objToBean(data, MonServiceData.class);
        serviceData.setPlatformCode(cascadeReq.getPublicInfo().getPlatformCode());
        AjaxResult result = this.remoteMonitorDataInService.saveServiceData(serviceData, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

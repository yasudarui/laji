package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonServiceData;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务指标信息数据上报
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
@Slf4j
public class MonServiceDataIssuedService extends DataOutCascadeTradeAbs {

    public MonServiceDataIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.MON_SERVICE_DATA_REPORT.getCode();
    }

    public void collectServiceData(MonServiceData serviceData){
        CascadeReq cascadeReq = this.fetchTrade(serviceData);
        this.execTrade(cascadeReq);
    }

}

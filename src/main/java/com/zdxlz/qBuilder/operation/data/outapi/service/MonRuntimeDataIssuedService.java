package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonRuntimeData;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 运行指标信息数据上报
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
@Slf4j
public class MonRuntimeDataIssuedService extends DataOutCascadeTradeAbs {

    public MonRuntimeDataIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.MON_RUNTIME_DATA_REPORT.getCode();
    }

    public void collectRuntimeData(MonRuntimeData runtimeData){
        log.info("接收运行指标上报信息【{}】", runtimeData);
        CascadeReq cascadeReq = this.fetchTrade(runtimeData);
        this.execTrade(cascadeReq);
    }

}

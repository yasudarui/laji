package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonCollectSecretKey;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 证书指标信息数据上报
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
@Slf4j
public class MonSecretKeyDataIssuedService extends DataOutCascadeTradeAbs {

    public MonSecretKeyDataIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode();
        this.bizType = BizTypeEnum.MON_SECRET_KEY_DATA_REPORT.getCode();
    }

    public void collectSecretKey(MonCollectSecretKey collectSecretKey) {
        CascadeReq cascadeReq = this.fetchTrade(collectSecretKey);
        this.execTrade(cascadeReq);
    }

}

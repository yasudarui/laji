package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.monitor.RemoteMonitorDataInService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonCollectSecretKey;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 密钥指标数据接入
 * @author zhouzilong
 * @date 2024-10-28
 */
@Service
public class MonSecretKeyDataService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteMonitorDataInService remoteMonitorDataInService;

    public MonSecretKeyDataService() {
        this.tradeType = BizTypeEnum.MON_SECRET_KEY_DATA_REPORT.getDesc();
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        MonCollectSecretKey secretKey = ObjectMapperUtil.objToBean(data, MonCollectSecretKey.class);
        secretKey.setPlatformCode(cascadeReq.getPublicInfo().getPlatformCode());
        AjaxResult result = this.remoteMonitorDataInService.saveSecretKeyData(secretKey, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

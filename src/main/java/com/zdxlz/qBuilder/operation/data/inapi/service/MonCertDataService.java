package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.monitor.RemoteMonitorDataInService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.monitor.entity.MonCertData;
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
public class MonCertDataService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteMonitorDataInService remoteMonitorDataInService;

    public MonCertDataService() {
        this.tradeType = BizTypeEnum.MON_CERT_DATA_REPORT.getDesc();
        this.tradeMode = BizModeEnum.REPORT.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        MonCertData certData = ObjectMapperUtil.objToBean(data, MonCertData.class);
        certData.setPlatformCode(cascadeReq.getPublicInfo().getPlatformCode());
        AjaxResult result = this.remoteMonitorDataInService.saveCertData(certData, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

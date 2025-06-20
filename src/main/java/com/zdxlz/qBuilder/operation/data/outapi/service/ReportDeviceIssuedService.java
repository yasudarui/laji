package com.zdxlz.qBuilder.operation.data.outapi.service;


import com.zdxlz.qBuilder.common.core.domain.report.dto.ReportDeviceCountDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表统计-设备统计-上报
 * @Date: 2024/10/28 16:32
 * @author caomin
 */
@Service
public class ReportDeviceIssuedService extends DataOutCascadeTradeAbs {

    public ReportDeviceIssuedService() {
        this.bizMode = BizModeEnum.REPORT.getCode() ;
        this.bizType = BizTypeEnum.DEVICE_REPORT.getCode();
    }

    public void reportDeviceCountOut(List<ReportDeviceCountDto> deviceCountList){
        CascadeReq cascadeReq = this.fetchTrade(deviceCountList);
        this.execTrade(cascadeReq);
    }

}

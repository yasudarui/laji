package com.zdxlz.qBuilder.operation.data.inapi.service;

import com.zdxlz.qBuilder.api.event.center.OperationInEventNotifyService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.eventcenter.dto.EventNotifyDto;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 事件通知数据接入
 * @author liugl
 * @date 2024-10-25
 */
@Service
public class EventNotifyInService extends DataInCascadeTradeAbs {

    @Resource
    private OperationInEventNotifyService operationInEventNotifyService;

    public EventNotifyInService() {
        this.tradeType = BizTypeEnum.EVENT_NOTICE_ISSUED.getDesc() ;
        this.tradeMode = BizModeEnum.ISSUED.getCode() ;
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Object data = cascadeReq.getData();
        EventNotifyDto eventNotifyDto = ObjectMapperUtil.objToBean(data,EventNotifyDto.class);
        AjaxResult result = operationInEventNotifyService.eventNotifyIn(eventNotifyDto, SecurityConstants.INNER);
        return ObjectMapperUtil.beanToJson(result);
    }
}

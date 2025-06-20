package com.zdxlz.qBuilder.operation.data.outapi.service;

import com.zdxlz.qBuilder.common.core.domain.eventcenter.dto.EventNotifyDto;
import com.zdxlz.qBuilder.operation.data.abs.DataOutCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 事件通知发出
 * @author liugl
 * @date 2024-10-25
 */
@Service
@Slf4j
public class EventNotifyIssuedService extends DataOutCascadeTradeAbs {

    public EventNotifyIssuedService() {
        this.bizMode = BizModeEnum.ISSUED.getCode() ;
        this.bizType = BizTypeEnum.EVENT_NOTICE_ISSUED.getCode();
    }

    public void issuedEventNotify(EventNotifyDto eventNotifyDto){
        if(ObjectUtils.isEmpty(eventNotifyDto.getPlatformCodes())){
            return;
        }
        CascadeReq cascadeReq = this.fetchTrade(eventNotifyDto);
        this.execTrade(cascadeReq, eventNotifyDto.getPlatformCodes());
    }

}

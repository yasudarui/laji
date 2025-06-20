package com.zdxlz.qBuilder.operation.data.abs;

import cn.hutool.core.util.ObjectUtil;
import com.zdxlz.qBuilder.common.core.exception.ServiceException;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.operation.data.dto.PublicInfoReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DataInCascadeTradeAbs extends DataInInterfaceForCascade {

    public final String execTrade(CascadeReq cascadeReq){
        try{
            this.initPlatform();
            this.validateBiz(cascadeReq);
            return this.bizProcess(cascadeReq);
        }catch (IllegalArgumentException | ServiceException e){
            return ObjectMapperUtil.beanToJson(AjaxResult.error(500,e.getMessage()));
        }catch (Exception e){
            log.error("交易请求失败:{}", String.valueOf(e));
            return ObjectMapperUtil.beanToJson(AjaxResult.error(500,"交易请求失败"));
        }
    }

    protected abstract String bizProcess(CascadeReq cascadeReq);

    private void validateBiz(CascadeReq cascadeReq){
        PublicInfoReq req = cascadeReq.getPublicInfo();
        if(ObjectUtil.isNull(BizModeEnum.getByCode(req.getBizMode()))){
            throw new ServiceException("不支持的交互类型");
        }
        if(ObjectUtil.isNull(BizTypeEnum.getBizType(req.getBizType()))){
            throw new ServiceException("不支持的交易类型");
        }
    }
}

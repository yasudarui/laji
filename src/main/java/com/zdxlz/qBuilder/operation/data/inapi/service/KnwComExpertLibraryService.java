package com.zdxlz.qBuilder.operation.data.inapi.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.zdxlz.qBuilder.api.knowledge.RemoteKnowledgeService;
import com.zdxlz.qBuilder.common.core.constant.SecurityConstants;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.DeleteComExpertLibraryDto;
import com.zdxlz.qBuilder.common.core.domain.knowledge.dto.SaveComExpertLibraryDto;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.log.enums.BusinessType;
import com.zdxlz.qBuilder.operation.data.abs.DataInCascadeTradeAbs;
import com.zdxlz.qBuilder.operation.data.dto.CascadeReq;
import com.zdxlz.qBuilder.operation.data.enums.BizModeEnum;
import com.zdxlz.qBuilder.operation.data.enums.BizTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 1 * @Author: zhongjian.zhang
 * 2 * @Date: 2024/10/24 11:49 上午
 * 密码专家库下发
 * 3
 */
@Service
public class KnwComExpertLibraryService extends DataInCascadeTradeAbs {

    @Autowired
    private RemoteKnowledgeService remoteKnowledgeService;

    public KnwComExpertLibraryService() {
        this.tradeType = BizTypeEnum.KNW_EXPERT_ISSUED.getDesc();
        this.tradeMode = BizModeEnum.ISSUED.getCode();
    }

    @Override
    protected String bizProcess(CascadeReq cascadeReq) {
        Map<String, Object> map = (Map<String, Object>) cascadeReq.getData();
        String operationType = (String) map.get("operationType");
        AjaxResult result = null;
        if (BusinessType.ASYNC.name().equals(operationType)) {
            String data = MapUtil.getStr(map, "data");
            result = remoteKnowledgeService.asyncKnwComExpertLibrary(data, SecurityConstants.INNER);
        } else {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            if (BusinessType.INSERT.name().equals(operationType)) {
                SaveComExpertLibraryDto saveComExpertLibraryDto = new SaveComExpertLibraryDto();
                BeanUtil.fillBeanWithMap(data, saveComExpertLibraryDto, true);
                result = remoteKnowledgeService.saveKnwComExpertLibrary(saveComExpertLibraryDto, SecurityConstants.INNER);
            } else if (BusinessType.UPDATE.name().equals(operationType)) {
                SaveComExpertLibraryDto saveComExpertLibraryDto = new SaveComExpertLibraryDto();
                BeanUtil.fillBeanWithMap(data, saveComExpertLibraryDto, true);
                result = remoteKnowledgeService.updateComExpertLibrary(saveComExpertLibraryDto, SecurityConstants.INNER);
            } else if (BusinessType.DELETE.name().equals(operationType)) {
                DeleteComExpertLibraryDto deleteComExpertLibraryDto = new DeleteComExpertLibraryDto();
                BeanUtil.fillBeanWithMap(data, deleteComExpertLibraryDto, true);
                result = remoteKnowledgeService.deleteComExpertLibraryById(deleteComExpertLibraryDto, SecurityConstants.INNER);
            } else {
                return ObjectMapperUtil.beanToJson(AjaxResult.error("operationType is not supported"));
            }
        }
        return ObjectMapperUtil.beanToJson(result);
    }
}

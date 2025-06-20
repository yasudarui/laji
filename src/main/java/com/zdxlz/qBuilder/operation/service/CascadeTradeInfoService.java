package com.zdxlz.qBuilder.operation.service;


import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeInfoDto;

import java.util.List;

/**
 * @author zhangtao
 * @date 2024-10-21
 */
public interface CascadeTradeInfoService {

    void saveCascade(CascadeTradeInfo cascadeTradeInfo);

    void update(CascadeTradeInfo cascadeTradeInfo);
    /**
     * 查询交易信息列表
     *
     * @param queryPageCascadeTradeInfoDto
     * @return 两级交互调用情况列表
     */
    List<CascadeTradeInfo> listCascadeTradeInfos(QueryPageCascadeTradeInfoDto queryPageCascadeTradeInfoDto);

    /**
     * 根据交易ID查询两级交互详情
     *
     * @param tradeId 交易Id
     * @return 两级交互详情
     */
    CascadeTradeInfo getCascadeTradeInfoDetailByTradeId(String tradeId);

    List<Object> getCascadeTradeInfoDropDown();
}

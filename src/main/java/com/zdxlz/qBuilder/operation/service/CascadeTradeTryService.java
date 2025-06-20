package com.zdxlz.qBuilder.operation.service;


import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeTry;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;

import java.util.List;

/**
 * @author zhangtao
 * @date 2024-10-21
 */
public interface CascadeTradeTryService {

    void save(CascadeTradeTry cascadeTradeTry);
    /**
     * 查询两级互联交易重试记录
     *
     * @param queryPageCascadeTradeTryDto 查询两级互联交易重试记录入参
     * @return 两级互联交易重试记录数据
     */
    List<CascadeTradeTry> listCascadeTradeTrys(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto);
}

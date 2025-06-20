package com.zdxlz.qBuilder.operation.service;


import com.zdxlz.qBuilder.common.core.domain.operation.dto.AbandonCompensateDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.CompensateCascadeTradeDto;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeCompensate;

import java.util.List;

/**
 * @author zhangtao
 * @date 2024-10-22
 */
public interface CascadeTradeCompensateService {


    /**
     * 分页查询两级互联交易补偿记录
     *
     * @param queryPageCascadeTradeTryDto
     * @return
     */
    List<CascadeTradeCompensate> listCascadeTradeCompensates(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto);

    /**
     * 两级互联交易补偿
     * @param compensateCascadeTradeDto
     * @return
     */
    Boolean compensate(CompensateCascadeTradeDto compensateCascadeTradeDto);

    /**
     * 放弃补偿
     * @param abandonCompensateDto
     * @return
     */
    Boolean abandonCompensate(AbandonCompensateDto abandonCompensateDto);
}

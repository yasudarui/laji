package com.zdxlz.qBuilder.operation.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zdxlz.qBuilder.common.core.constant.Constants;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeTryDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeTry;
import com.zdxlz.qBuilder.operation.mapper.CascadeTradeTryMapper;
import com.zdxlz.qBuilder.operation.service.CascadeTradeTryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author zhangtao
 * @date 2024-10-21
 */
@Service
@Slf4j
public class CascadeTradeTryServiceImpl implements CascadeTradeTryService {

    @Resource
    private CascadeTradeTryMapper cascadeTradeTryMapper;

    @Override
    public void save(CascadeTradeTry cascadeTradeTry) {
        this.cascadeTradeTryMapper.insert(cascadeTradeTry);
    }

    /**
     * 查询两级互联交易重试记录
     *
     * @param queryPageCascadeTradeTryDto 查询两级互联交易重试记录入参
     * @return 两级互联交易重试记录数据
     */
    @Override
    public List<CascadeTradeTry> listCascadeTradeTrys(QueryPageCascadeTradeTryDto queryPageCascadeTradeTryDto) {
        return cascadeTradeTryMapper.selectList(Wrappers.<CascadeTradeTry>lambdaQuery()
                .eq(CascadeTradeTry::getTradeId, queryPageCascadeTradeTryDto.getTradeId())
                .eq(CascadeTradeTry::getDelFlag, Constants.DEL_FLAG_ACTIVE).orderByDesc(CascadeTradeTry::getCreateTime));

    }
}

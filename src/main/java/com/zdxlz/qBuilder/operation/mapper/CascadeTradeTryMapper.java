package com.zdxlz.qBuilder.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeTry;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易重试表 数据层
 *
 * @author zhangtao
 * @date 2024-10-21
 */
@Mapper
public interface CascadeTradeTryMapper extends BaseMapper<CascadeTradeTry> {

}

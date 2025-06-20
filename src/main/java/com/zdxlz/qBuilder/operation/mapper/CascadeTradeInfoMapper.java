package com.zdxlz.qBuilder.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeInfoDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.domain.operation.vo.QueryCascadeTradeInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 交易表 数据层
 *
 * @author zhangtao
 * @date 2024-10-21
 */
@Mapper
public interface CascadeTradeInfoMapper extends BaseMapper<CascadeTradeInfo> {

    List<QueryCascadeTradeInfoVo> listCascadeTradeInfos(QueryPageCascadeTradeInfoDto queryPageCascadeTradeInfoDto);
}

package com.zdxlz.qBuilder.operation.service.impl;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdxlz.qBuilder.common.core.constant.Constants;
import com.zdxlz.qBuilder.common.core.domain.operation.dto.QueryPageCascadeTradeInfoDto;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.operation.mapper.CascadeTradeInfoMapper;
import com.zdxlz.qBuilder.operation.service.CascadeTradeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * @author zhangtao
 * @date 2024-10-21
 */
@Service
@Slf4j
public class CascadeTradeInfoServiceImpl extends ServiceImpl<CascadeTradeInfoMapper, CascadeTradeInfo> implements CascadeTradeInfoService {

    public void saveCascade(CascadeTradeInfo cascadeTradeInfo) {
        this.save(cascadeTradeInfo);
    }

    /**
     * 查询交易信息列表
     *
     * @param queryPageCascadeTradeInfoDto
     * @return 两级交互调用情况列表
     */
    @Override
    public List<CascadeTradeInfo> listCascadeTradeInfos(QueryPageCascadeTradeInfoDto queryPageCascadeTradeInfoDto) {
        return this.list(Wrappers.<CascadeTradeInfo>lambdaQuery()
                .like(StringUtils.isNotBlank(queryPageCascadeTradeInfoDto.getTradeInterface()), CascadeTradeInfo::getTradeInterface, queryPageCascadeTradeInfoDto.getTradeInterface())
                .eq(StringUtils.isNotBlank(queryPageCascadeTradeInfoDto.getCompensateStatus()), CascadeTradeInfo::getCompensateStatus, queryPageCascadeTradeInfoDto.getCompensateStatus())
                .eq(CascadeTradeInfo::getDelFlag, Constants.DEL_FLAG_ACTIVE)
                .eq(StringUtils.isNotBlank(queryPageCascadeTradeInfoDto.getReqPlatformCode()), CascadeTradeInfo::getReqPlatformCode, queryPageCascadeTradeInfoDto.getReqPlatformCode())
                .eq(StringUtils.isNotBlank(queryPageCascadeTradeInfoDto.getResPlatformCode()), CascadeTradeInfo::getResPlatformCode, queryPageCascadeTradeInfoDto.getResPlatformCode())
                .like(StringUtils.isNotBlank(queryPageCascadeTradeInfoDto.getInterfaceAddress()), CascadeTradeInfo::getInterfaceAddress, queryPageCascadeTradeInfoDto.getInterfaceAddress())
                .between(Objects.nonNull(queryPageCascadeTradeInfoDto.getReqStartTime()) && Objects.nonNull(queryPageCascadeTradeInfoDto.getReqEndTime()), CascadeTradeInfo::getReqTime, queryPageCascadeTradeInfoDto.getReqStartTime(), queryPageCascadeTradeInfoDto.getReqEndTime()).orderByDesc(CascadeTradeInfo::getCreateTime));

    }

    @Override
    public void update(CascadeTradeInfo cascadeTradeInfo) {
        this.updateById(cascadeTradeInfo);
    }

    /**
     * 根据交易ID查询两级交互详情
     *
     * @param tradeId 交易Id
     * @return 两级交互详情
     */
    @Override
    public CascadeTradeInfo getCascadeTradeInfoDetailByTradeId(String tradeId) {
        return this.getOne(Wrappers.<CascadeTradeInfo>lambdaQuery()
                .eq(CascadeTradeInfo::getTradeId, tradeId)
                .eq(CascadeTradeInfo::getDelFlag, Constants.DEL_FLAG_ACTIVE));
    }

    @Override
    public List<Object> getCascadeTradeInfoDropDown() {
        QueryWrapper<CascadeTradeInfo> queryWrapper = new QueryWrapper<CascadeTradeInfo>().select("distinct res_platform_code").eq("del_flag", Constants.DEL_FLAG_ACTIVE);
        return this.listObjs(queryWrapper);

    }
}

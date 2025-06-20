package com.zdxlz.qBuilder.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zdxlz.qBuilder.common.core.domain.cascade.CascadePlatform;
import com.zdxlz.qBuilder.operation.mapper.CascadePlatformMapper;
import com.zdxlz.qBuilder.operation.service.CascadePlatformService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CascadePlatformServiceImpl implements CascadePlatformService {

    @Resource
    private CascadePlatformMapper cascadePlatformMapper;

    @Override
    public List<CascadePlatform> list(List<String> platformCodeList) {
        LambdaQueryWrapper<CascadePlatform> queryWrapper = new LambdaQueryWrapper<CascadePlatform>() ;
        queryWrapper.eq(CascadePlatform::getStatus,"1").eq(CascadePlatform::getDelFlag,"0") ;
        if(CollectionUtils.isNotEmpty(platformCodeList)){
            queryWrapper.in(CascadePlatform::getPlatformCode,platformCodeList);
        }
        return this.cascadePlatformMapper.selectList(queryWrapper);
    }
}

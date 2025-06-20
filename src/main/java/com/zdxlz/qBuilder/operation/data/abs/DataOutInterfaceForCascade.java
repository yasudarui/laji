package com.zdxlz.qBuilder.operation.data.abs;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zdxlz.qBuilder.common.core.domain.cascade.CascadePlatform;
import com.zdxlz.qBuilder.common.core.domain.operation.entity.CascadeTradeInfo;
import com.zdxlz.qBuilder.common.core.domain.system.entity.SysConfig;
import com.zdxlz.qBuilder.common.redis.service.RedisService;
import com.zdxlz.qBuilder.operation.mapper.SysConfigMapper;
import com.zdxlz.qBuilder.operation.service.CascadePlatformService;
import com.zdxlz.qBuilder.operation.service.CascadeTradeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DataOutInterfaceForCascade {

    @Autowired
    protected CascadeTradeInfoService cascadeTradeInfoService;

    @Autowired
    protected CascadePlatformService cascadePlatformService ;
    protected String bizType;
    protected String bizMode;

    @Resource
    protected SysConfigMapper sysConfigMapper ;

    @Autowired
    protected RedisService redisService;

    protected String platformCode;

    protected Integer tryAgainCount;

    protected Integer tryAgainTime;

    protected String dataOutUrl ;

    protected String tokenUrl ;

    protected String platformType ;
    public void init (){
        SysConfig platformCodeConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"sys.platform.code")) ;
        SysConfig tryAgainCountConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"sys.platform.try.again.count")) ;
        SysConfig tryAgainTimeConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"sys.platform.try.again.time")) ;
        SysConfig dataOutUrlConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"data.out.url")) ;
        SysConfig platformTypeConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"sys.platform.type")) ;
        SysConfig dataOutTokenUrlConfig = this.sysConfigMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey,"data.out.token.url")) ;
        this.platformCode = ObjectUtil.isNotEmpty(platformCodeConfig) ? platformCodeConfig.getConfigValue() : "";
        this.tryAgainCount = ObjectUtil.isNotEmpty(tryAgainCountConfig) ? Integer.valueOf(tryAgainCountConfig.getConfigValue()) : 1;
        this.tryAgainTime = ObjectUtil.isNotEmpty(tryAgainTimeConfig) ? Integer.valueOf(tryAgainTimeConfig.getConfigValue()) : 10;
        this.dataOutUrl = ObjectUtil.isNotEmpty(dataOutUrlConfig) ? dataOutUrlConfig.getConfigValue() : "";
        this.platformType = ObjectUtil.isNotEmpty(platformTypeConfig) ? platformTypeConfig.getConfigValue() : "";
        this.tokenUrl=ObjectUtil.isNotEmpty(dataOutTokenUrlConfig) ? dataOutTokenUrlConfig.getConfigValue() : "";
    }

    public void saveTradeInfo(CascadeTradeInfo cascadeTradeInfo){
        cascadeTradeInfo.setBiType(this.bizType);
        cascadeTradeInfo.setBizMode(this.bizMode);
        cascadeTradeInfo.setCreateTime(new Date());
        cascadeTradeInfo.setUpdateTime(new Date());
        cascadeTradeInfo.setReqPlatformCode(this.platformCode);
        cascadeTradeInfo.setTrgAgainNum(0);
        cascadeTradeInfo.setCompensateNum(0);
        this.cascadeTradeInfoService.saveCascade(cascadeTradeInfo);
    }

    public void updateTradeInfo(CascadeTradeInfo cascadeTradeInfo){
        cascadeTradeInfo.setUpdateTime(new Date());
        this.cascadeTradeInfoService.update(cascadeTradeInfo);
    }

    public List<CascadePlatform> list(List<String> platformList){
        return this.cascadePlatformService.list(platformList);
    }
}

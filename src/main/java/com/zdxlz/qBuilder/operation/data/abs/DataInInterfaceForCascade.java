package com.zdxlz.qBuilder.operation.data.abs;

import com.zdxlz.qBuilder.common.redis.service.RedisService;
import com.zdxlz.qBuilder.operation.data.constant.Constants;
import com.zdxlz.qBuilder.operation.data.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DataInInterfaceForCascade {

    @Autowired
    private RedisService redisService;

    protected String platformCode;

    protected String tradeType;

    protected String tradeMode;
    protected void initPlatform(){
        String platformCode = RequestUtil.getPlatformCode();
        this.platformCode = platformCode ;
        String key = String.format("%s_%s_%s",Constants.TOKEN_IN,Constants.TOKEN_PREFIX,platformCode);
        String token = RequestUtil.getToken(Constants.TOKEN_PREFIX) ;
        this.redisService.setCacheObject(key,token,7200L, TimeUnit.SECONDS); ;
    }
}

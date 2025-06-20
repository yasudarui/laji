package com.zdxlz.qBuilder.operation.data.util;

import com.zdxlz.qBuilder.common.core.exception.ServiceException;
import com.zdxlz.qBuilder.common.core.utils.ObjectMapperUtil;
import com.zdxlz.qBuilder.common.core.utils.SpringUtils;
import com.zdxlz.qBuilder.common.core.utils.StringUtils;
import com.zdxlz.qBuilder.common.core.utils.http.HttpUtils;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.redis.service.RedisService;
import com.zdxlz.qBuilder.operation.data.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
public class GetTokenUtil {

    public static String getToken (String reqPlatformCode,String resPlatformCode,String tokenUrl){
        String redisKey = String.format("%s_%s_%s_%s", Constants.TOKEN_OUT,Constants.TOKEN_PREFIX ,reqPlatformCode,resPlatformCode) ;
        RedisService redisService = SpringUtils.getBean(RedisService.class);
        String accessToken = redisService.getCacheObject(redisKey) ;
        if(StringUtils.isBlank(accessToken)){
            String result = HttpUtils.sendGet(tokenUrl);
            log.info("获取token结果:{}",result);
            if(StringUtils.isBlank(result)){
                throw new ServiceException("获取accessToken令牌失败");
            }
            AjaxResult ajaxResult = ObjectMapperUtil.jsonToBean(result,AjaxResult.class);
            if(!ajaxResult.isSuccess()){
                throw new ServiceException(String.valueOf(ajaxResult.get(AjaxResult.MSG_TAG)));
            }
            Map<String, String> data = (Map<String, String>) ajaxResult.get("data");
            accessToken = data.get(Constants.TOKEN_PREFIX) ;
            log.info("accessToken:{}",accessToken);
            redisService.setCacheObject(redisKey,accessToken,3600L, TimeUnit.SECONDS); ;
        }
        return accessToken ;
    }

}

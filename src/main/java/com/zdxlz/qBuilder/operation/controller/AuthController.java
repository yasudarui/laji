package com.zdxlz.qBuilder.operation.controller;

import cn.hutool.core.util.IdUtil;
import com.zdxlz.qBuilder.common.core.utils.probe.sm2.SM2Utils;
import com.zdxlz.qBuilder.common.core.utils.sign.Base64;
import com.zdxlz.qBuilder.common.core.web.domain.AjaxResult;
import com.zdxlz.qBuilder.common.redis.service.RedisService;
import com.zdxlz.qBuilder.operation.data.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/operate/api/v1/auth")
@AllArgsConstructor
@Api(tags = "获取token")
public class AuthController {


    private final RedisService redisService;



    @GetMapping("/token/{platformCode}")
    @ApiOperation(value = "根据platformCode获取token")
    public AjaxResult token( @PathVariable("platformCode") String platformCode) throws Exception {
        //token标识，token key，来源码
        String key = String.format("%s_%s_%s",Constants.TOKEN_IN,Constants.TOKEN_PREFIX,platformCode);
        String value = this.redisService.getCacheObject(key);
        Map<String, String> map = new HashMap<>();
        if (value != null) {
            map.put(Constants.TOKEN_PREFIX, value);
        }else{
            String uuid = IdUtil.fastSimpleUUID();
            String originKey = platformCode + uuid;
            value = Base64.encode(SM2Utils.sign(originKey.getBytes(StandardCharsets.UTF_8), platformCode.getBytes(StandardCharsets.UTF_8)));
            this.redisService.setCacheObject(key,value,7200L,TimeUnit.SECONDS); ;
            map.put(Constants.TOKEN_PREFIX, value);
        }
        return AjaxResult.success(map);
    }

}

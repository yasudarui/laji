package com.zdxlz.qBuilder.operation.data.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
public class RequestUtil {
    public static String getToken(String key){
        HttpServletRequest request = getRequest();
        return request.getHeader(key);
    }

    public static void getHeaders(){
        HttpServletRequest request = getRequest();
        Enumeration<String> enumerations =  request.getHeaderNames();
        while (enumerations.hasMoreElements()) {
            String headerName = enumerations.nextElement();
            String headerValue = request.getHeader(headerName);
           log.info(headerName + ": " + headerValue);
        }
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        // 取得request对象
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            return request;
        } catch (Exception e) {
            log.error("获取request对象失败:",e);
        }
        return null;
    }

    public static String getPlatformCode(){
        HttpServletRequest request = getRequest();
        return request.getHeader("platformCode");
    }
}

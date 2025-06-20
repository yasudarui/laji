package com.zdxlz.qBuilder.operation;

import com.zdxlz.qBuilder.common.core.annotation.EnableRyFeignClients;
import com.zdxlz.qBuilder.common.security.annotation.EnableCustomConfig;
import com.zdxlz.qBuilder.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.zdxlz.qBuilder.operation", "com.zdxlz.qBuilder.common.core.config"})
public class OperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class, args);
        System.out.println("=====商密运维模块启动成功=====");
    }

}

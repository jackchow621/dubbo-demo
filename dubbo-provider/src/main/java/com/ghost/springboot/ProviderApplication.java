package com.ghost.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//exclude scan datasource component
@EnableDubboConfig
public class ProviderApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ProviderApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


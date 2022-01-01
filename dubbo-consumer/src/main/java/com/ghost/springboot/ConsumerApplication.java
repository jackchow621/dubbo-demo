package com.ghost.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//exclude scan datasource component
@EnableDubbo
public class ConsumerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ConsumerApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


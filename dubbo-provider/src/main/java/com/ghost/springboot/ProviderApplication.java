package com.ghost.springboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//exclude scan datasource component
public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run();
    }

}


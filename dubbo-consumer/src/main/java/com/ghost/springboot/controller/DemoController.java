package com.ghost.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.nacos.api.exception.NacosException;
import com.ghost.springboot.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author zoulinjun
 * @Title: DemoController
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/1/2416:10
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Reference(version = "${demo.service.version}")
    private DemoService demoService;

    @RequestMapping(value = "/get", method = GET)
    public String saySomething(String name) {
        return demoService.sayHello(name);
    }
}

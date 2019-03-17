package com.ghost.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.ghost.springboot.config.redis.impl.RedisClientServiceImpl;
import com.ghost.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zoulinjun
 * @Title: DemoServiceImpl
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/1/2416:04S
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {

    @Value("${demo.service.name}")
    private String serviceName;

    @Autowired
    private RedisClientServiceImpl redisClientService;

    @Override
    public String sayHello(String name) {
        RpcContext rpcContext = RpcContext.getContext();
        System.out.println(String.format("Service [name :%s , port : %d] %s(\"%s\") : Hello,%s",
                serviceName,
                rpcContext.getLocalPort(),
                rpcContext.getMethodName(),
                name,
                name));
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

}

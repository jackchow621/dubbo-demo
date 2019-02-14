package com.ghost.springboot.config.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author zoulinjun
 * @Title: RedisDev
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/2/14 14:17
 */
public class RedisDev extends RedisDevConfiguration {
    @Resource(name = "redisDevTemplate")
    private StringRedisTemplate temple;

    public StringRedisTemplate getTemple() {
        return temple;
    }

    public RedisDev setTemple(StringRedisTemplate temple) {
        this.temple = temple;
        return this;
    }
}

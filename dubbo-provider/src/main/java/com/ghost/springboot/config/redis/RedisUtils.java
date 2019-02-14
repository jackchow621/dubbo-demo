package com.ghost.springboot.config.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.ReflectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Field;

/**
 * @author zoulinjun
 * @Title: RedisUtils
 * @ProjectName springboot-nacosa
 * @Description: TODO
 * @date 2019/2/14 14:02
 */
public class RedisUtils implements InitializingBean {

    @Autowired
    private RedisDev redisDev;

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() {
        Field jedisPoolField = ReflectionUtils.findField(RedisConnectionFactory.class, "jedisPool");
        ReflectionUtils.makeAccessible(jedisPoolField);
        jedisPool = (JedisPool) ReflectionUtils.getField(jedisPoolField, redisDev.getTemple().getConnectionFactory());
    }

    public Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        } else {
            afterPropertiesSet();
            return jedisPool.getResource();
        }
    }
}

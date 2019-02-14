package com.ghost.springboot.config.redis;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zoulinjun
 * @Title: RedisDevConfiguration
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/2/14 11:44
 */
@Configuration
public class RedisDevConfiguration {

    @Bean(name = "redisDevTemplate")
    public StringRedisTemplate redisTemplate(@Value("${spring.redis.jedis.redis-dev.host}") String hostName,
                                             @Value("${spring.redis.jedis.redis-dev.port}") int port, @Value("${spring.redis.jedis.redis-dev.password}") String password,
                                             @Value("${spring.redis.jedis.redis-dev.testOnBorrow}") boolean testOnBorrow,
                                             @Value("${spring.redis.jedis.redis.pool.max-idle}") int maxIdle, @Value("${spring.redis.jedis.redis.pool.max-active}") int maxTotal,
                                             @Value("${spring.redis.jedis.redis.database}") int index, @Value("${spring.redis.jedis.redis.pool.max-wait}") long maxWaitMillis) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(connectionFactory(hostName, port, password, maxIdle, maxTotal, index, maxWaitMillis, testOnBorrow));

        return temple;
    }

    public RedisConnectionFactory connectionFactory(String hostName, int port, String password, int maxIdle,
                                                    int maxTotal, int index, long maxWaitMillis, boolean testOnBorrow) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);
        if (StringUtils.isNotEmpty(password)) {
            jedis.setPassword(password);
        }
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }
}

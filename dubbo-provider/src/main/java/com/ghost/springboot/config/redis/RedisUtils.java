package com.ghost.springboot.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @author zoulinjun
 * @Title: RedisUtils
 * @ProjectName springboot-nacosa
 * @Description: TODO
 * @date 2019/2/14 14:02
 */
@Service
public class RedisUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private  JedisPool jedisPool = null;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private  int maxIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    private  int maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private  long maxWaitMills;

    @Value("${spring.redis.jedis.redis-dev.testOnBorrow}")
    private  boolean testOnBorrow;

    @Value("${spring.redis.jedis.redis-dev.host}")
    private  String hostName;

    @Value("${spring.redis.jedis.redis-dev.port}")
    private  int port;

    @Value("${spring.redis.jedis.redis-dev.password}")
    private  String password;


    /**
     * 初始化JedisPool.
     */
    private void initJedisPool(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow,
                                      String hostName, int port, String password) {
        JedisPoolConfig config = poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow);

        jedisPool = new JedisPool(config, hostName, port);


    }

    private JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }

    /**
     * 获取JedisPool实例.
     *
     * @return
     */
    public synchronized  JedisPool getPool() {
        if (null == jedisPool) {
            initJedisPool(maxIdle,maxTotal, maxWaitMills, testOnBorrow, hostName, port, password);
        }
        return jedisPool;
    }

    /**
     * 从连接池获取Jedis实例.
     *
     * @return Jedis Jedis
     * @throws Exception 异常信息
     */
    public synchronized  Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
        } catch (Exception e) {
            logger.error("get redis connection fail:{}", e);
        }
        return jedis;
    }
}

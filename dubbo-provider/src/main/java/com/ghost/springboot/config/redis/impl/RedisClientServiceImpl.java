package com.ghost.springboot.config.redis.impl;

import com.ghost.springboot.config.redis.RedisClientService;
import com.ghost.springboot.config.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zoulinjun
 * @Title: RedisClientTemplateImpl
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/1/2515:40
 */
@Service
public class RedisClientServiceImpl implements RedisClientService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Long lpush(String key, String... value) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("method lpush occur error,key:" + key + ",value:" + value, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long rpush(String key, String... value) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.rpush(key, value);
        } catch (Exception e) {
            logger.error("method rpush occur error,key:" + key + ",value:" + value, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public String rpop(String key) {
        String result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.rpop(key);
        } catch (Exception e) {
            logger.error("method rpop occur error,key:" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public String lpop(String key) {
        String result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.lpop(key);
        } catch (Exception e) {
            logger.error("method lpop occur error,key:" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        List<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("method lrange occur error,key:" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public String lindex(String key, long index) {
        String result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.lindex(key, index);
        } catch (Exception e) {
            logger.error("method lindex occur error,key:" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        List<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("method brpop occur error,key:" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public void setVal(String key, String value) {
        Jedis jedis = redisUtils.getJedis();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory set error!key=" + key + ",value=" + value, e);
        } finally {
            jedis.close();
        }
    }

    public void setVal(byte[] key, byte[] value) {
        Jedis jedis = redisUtils.getJedis();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory set error!key=" + key + ",value=" + value, e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long incrAndExpireAfterToday(String key) {
        Long retVal = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return retVal;
        }
        try {
            retVal = jedis.incr(key);
            Long ttl = jedis.ttl(key);
            if ((ttl.longValue() == -1L) && (ttl.longValue() != -2L)) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, 23);
                calendar.set(12, 59);
                calendar.set(13, 59);
                jedis.expireAt(key, calendar.getTimeInMillis() / 1000L);
            }
        } catch (Exception e) {
            logger.error("incrAndExpireAfterToday error !key={}", key, e);
        } finally {
            jedis.close();
        }
        return retVal;
    }

    @Override
    public Long srem(String skey, String member) {
        Long retVal = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return retVal;
        }
        try {
            retVal = jedis.srem(skey, new String[]{member});
        } catch (Exception e) {
            logger.error("srem error !skey={}, member={}", new Object[]{skey, member, e});
        } finally {
            jedis.close();
        }
        return retVal;
    }

    @Override
    public void hsetVal(String key, String field, String value) {
        Jedis jedis = redisUtils.getJedis();
        try {
            jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hset error!key=" + key + ",field=" + field + ",value=" + value, e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public void setValEx(String key, String value, int seconds) {
        Jedis jedis = redisUtils.getJedis();
        try {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory setValEx error!key=" + key + ",value=" + value, e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getVal(String key) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory getVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    public byte[] getVal(byte[] key) {
        byte[] value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory getVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public String hgetVal(String key, String field) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hgetVal error!key=" + key + ",field=" + field + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Boolean exists(String key) {
        Boolean value = Boolean.valueOf(false);
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.exists(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory exists error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Boolean hexists(String key, String field) {
        Boolean value = Boolean.valueOf(false);
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hexists(key, field);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hexists error!key=" + key + ",field=" + field + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public String delVal(String key) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            jedis.del(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory delVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    public String delVal(byte[] key) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            jedis.del(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory delVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public String scriptLoad(String script) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.scriptLoad(script);
        } catch (Exception e) {
            logger.error("jedisPoolFactory scriptLoad error!script=" + script, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        Object value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.eval(script, keyCount, params);
        } catch (Exception e) {
            logger.error("jedisPoolFactory eval error!eval=" + script, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long expire(String key, int seconds) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("jedisPoolFactory expire error!key=" + key, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long ttl(String key) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.ttl(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory expire error!key=" + key, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long lrem(String key, long count, String str) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.lrem(key, count, str);
        } catch (Exception e) {
            logger.error("jedisPoolFactory delVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long linsert(String key, String pivot, String str) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.linsert(key, ListPosition.BEFORE, pivot, str);
        } catch (Exception e) {
            logger.error("jedisPoolFactory delVal error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long hdel(String key, String... fields) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hdel(key, fields);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hdel error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Long hlen(String key) {
        Long value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hlen(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hlen error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Set<String> hkeys(String key) {
        Set<String> value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hkeys(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hkeys error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public List<String> hvals(String key) {
        List<String> value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hvals(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hvals error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hgetAll(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hgetAll error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        String value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hmset(key, hash);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hmset error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        List<String> value = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return value;
        }
        try {
            value = jedis.hmget(key, fields);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hmget error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return value;
    }

    @Deprecated
    @Override
    public Long setValNx(String key, String value) {
        Long result = Long.valueOf(0L);
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory set error!key=" + key + ",value=" + value, e);
            throw e;
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Set<String> keys(String pattern) {
        Set<String> result = new HashSet();
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
//            shardedJedis = this.dataSource.getShardedJedis();
//            for (Jedis jedis : shardedJedis.getAllShards()) {
            result.addAll(jedis.keys(pattern));
//            }
        } catch (Exception e) {
            logger.error("jedisPoolFactory set error!key=" + pattern, e);
            throw e;
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zadd(key, score, member);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zadd error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long zrem(String key, String member) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zrem(key, new String[]{member});
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrem error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zremrangeByScore(key, start, end);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zremrangeByScore error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long zcount(String key, double min, double max) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zcount(key, min, max);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zcount error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long zcard(String key) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zcard(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zcard error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public LinkedHashSet<String> zrevrange(String key, long start, long end) {
        LinkedHashSet<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = (LinkedHashSet) jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrevrange error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public LinkedHashSet<String> zrevrangeByScore(String key, long start, long end) {
        LinkedHashSet<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = (LinkedHashSet) jedis.zrevrangeByScore(key, start, end);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrevrangeByScore error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long incrBy(String key, long num) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.incrBy(key, num);
        } catch (Exception e) {
            logger.error("jedisPoolFactory incrBy error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.incr(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory incr error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long llen(String key) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.llen(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory llen error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long sadd(String key, String... members) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sadd(key, members);
        } catch (Exception e) {
            logger.error("jedisPoolFactory sadd error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long scard(String key) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.scard(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory scard error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Set<String> smember(String key) {
        Set<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.smembers(key);
        } catch (Exception e) {
            logger.error("jedisPoolFactory smember error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.hsetnx(key, field, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory hsetnx error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long hincrBy(String key, String field, Long value) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.hincrBy(key, field, value.longValue());
        } catch (Exception e) {
            logger.error("jedisPoolFactory hincrBy error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public String ltrim(String key, Long start, Long end) {
        String result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.ltrim(key, start.longValue(), end.longValue());
        } catch (Exception e) {
            logger.error("jedisPoolFactory ltrim error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        String result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            logger.error("jedisPoolFactory ltrim error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Boolean sismember(String key, String member) {
        Boolean result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("jedisPoolFactory sismember error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        Set<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrange error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Set<String> result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrangeByScore error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public Long expireAt(String key, int time) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.expireAt(key, time);
        } catch (Exception e) {
            logger.error("jedisPoolFactory expireAt error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Deprecated
    @Override
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
//            Iterator localIterator = shardedJedis.getAllShards().iterator();
//            if (localIterator.hasNext()) {
//                Jedis jedis = (Jedis) localIterator.next();
//                result = jedis;
//            }
            jedis = redisUtils.getJedis();
            if (jedis == null) {
                return null;
            }
            return jedis;
        } catch (Exception e) {
            logger.error("jedisPoolFactory get jedis error!", e);
            throw e;
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long zrank(String key, String member) {
        Long result = null;
        Jedis jedis = redisUtils.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zrank(key, member);
        } catch (Exception e) {
            logger.error("jedisPoolFactory zrank error!key=" + key, e);
        } finally {
            jedis.close();
        }
        return result;
    }

}

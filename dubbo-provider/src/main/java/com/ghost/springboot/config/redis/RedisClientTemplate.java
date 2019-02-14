package com.ghost.springboot.config.redis;

import redis.clients.jedis.Jedis;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zoulinjun
 * @Title: RedisClientTemplate
 * @ProjectName springboot-nacos
 * @Description: TODO
 * @date 2019/1/2515:38
 */
public abstract interface RedisClientTemplate {
    public abstract Long lpush(String paramString, String... paramVarArgs);

    public abstract String rpop(String paramString);

    public abstract String lpop(String paramString);

    public abstract List<String> brpop(int paramInt, String paramString);

    public abstract Long rpush(String paramString, String... paramVarArgs);

    public abstract List<String> lrange(String paramString, long paramLong1, long paramLong2);

    public abstract void setVal(String paramString1, String paramString2);

    public abstract void hsetVal(String paramString1, String paramString2, String paramString3);

    public abstract void setValEx(String paramString1, String paramString2, int paramInt);

    public abstract String getVal(String paramString);

    public abstract String hgetVal(String paramString1, String paramString2);

    public abstract Boolean exists(String paramString);

    public abstract Boolean hexists(String paramString1, String paramString2);

    public abstract String delVal(String paramString);

    public abstract String scriptLoad(String paramString);

    public abstract Object eval(String paramString, int paramInt, String... paramVarArgs);

    public abstract Long expire(String paramString, int paramInt);

    public abstract Long ttl(String paramString);

    public abstract Long lrem(String paramString1, long paramLong, String paramString2);

    public abstract Long linsert(String paramString1, String paramString2, String paramString3);

    public abstract String lindex(String paramString, long paramLong);

    public abstract Long hdel(String paramString, String... paramVarArgs);

    public abstract Long hlen(String paramString);

    public abstract Set<String> hkeys(String paramString);

    public abstract List<String> hvals(String paramString);

    public abstract Map<String, String> hgetAll(String paramString);

    public abstract String hmset(String paramString, Map<String, String> paramMap);

    public abstract List<String> hmget(String paramString, String... paramVarArgs);

    public abstract Long setValNx(String paramString1, String paramString2);

    public abstract Set<String> keys(String paramString);

    public abstract Long zadd(String paramString1, double paramDouble, String paramString2);

    public abstract Long zremrangeByScore(String paramString, double paramDouble1, double paramDouble2);

    public abstract Long zcount(String paramString, double paramDouble1, double paramDouble2);

    public abstract Long zcard(String paramString);

    public abstract LinkedHashSet<String> zrevrange(String paramString, long paramLong1, long paramLong2);

    public abstract LinkedHashSet<String> zrevrangeByScore(String paramString, long paramLong1, long paramLong2);

    public abstract Jedis getJedis();

    public abstract Long incrBy(String paramString, long paramLong);

    public abstract Long incr(String paramString);

    public abstract Long llen(String paramString);

    public abstract Long sadd(String paramString, String... paramVarArgs);

    public abstract Long scard(String paramString);

    public abstract Set<String> smember(String paramString);

    public abstract Boolean sismember(String paramString1, String paramString2);

    public abstract Long expireAt(String paramString, int paramInt);

    public abstract Set<String> zrange(String paramString, long paramLong1, long paramLong2);

    public abstract Long incrAndExpireAfterToday(String paramString);

    public abstract Long srem(String paramString1, String paramString2);

    public abstract Long zrem(String paramString1, String paramString2);

    public abstract Long hsetnx(String paramString1, String paramString2, String paramString3);

    public abstract Long hincrBy(String paramString1, String paramString2, Long paramLong);

    public abstract String ltrim(String paramString, Long paramLong1, Long paramLong2);

    public abstract String getSet(String paramString1, String paramString2);

    public abstract Long zrank(String paramString1, String paramString2);
}

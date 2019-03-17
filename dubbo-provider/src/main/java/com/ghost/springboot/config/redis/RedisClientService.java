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
public interface RedisClientService {
    public  Long lpush(String paramString, String... paramVarArgs);

    public  String rpop(String paramString);

    public  String lpop(String paramString);

    public  List<String> brpop(int paramInt, String paramString);

    public  Long rpush(String paramString, String... paramVarArgs);

    public  List<String> lrange(String paramString, long paramLong1, long paramLong2);

    public  void setVal(String paramString1, String paramString2);

    public  void hsetVal(String paramString1, String paramString2, String paramString3);

    public  void setValEx(String paramString1, String paramString2, int paramInt);

    public  String getVal(String paramString);

    public  String hgetVal(String paramString1, String paramString2);

    public  Boolean exists(String paramString);

    public  Boolean hexists(String paramString1, String paramString2);

    public  String delVal(String paramString);

    public  String scriptLoad(String paramString);

    public  Object eval(String paramString, int paramInt, String... paramVarArgs);

    public  Long expire(String paramString, int paramInt);

    public  Long ttl(String paramString);

    public  Long lrem(String paramString1, long paramLong, String paramString2);

    public  Long linsert(String paramString1, String paramString2, String paramString3);

    public  String lindex(String paramString, long paramLong);

    public  Long hdel(String paramString, String... paramVarArgs);

    public  Long hlen(String paramString);

    public  Set<String> hkeys(String paramString);

    public  List<String> hvals(String paramString);

    public  Map<String, String> hgetAll(String paramString);

    public  String hmset(String paramString, Map<String, String> paramMap);

    public  List<String> hmget(String paramString, String... paramVarArgs);

    public  Long setValNx(String paramString1, String paramString2);

    public  Set<String> keys(String paramString);

    public  Long zadd(String paramString1, double paramDouble, String paramString2);

    public  Long zremrangeByScore(String paramString, double paramDouble1, double paramDouble2);

    public  Long zcount(String paramString, double paramDouble1, double paramDouble2);

    public  Long zcard(String paramString);

    public  LinkedHashSet<String> zrevrange(String paramString, long paramLong1, long paramLong2);

    public  LinkedHashSet<String> zrevrangeByScore(String paramString, long paramLong1, long paramLong2);

    public  Jedis getJedis();

    public  Long incrBy(String paramString, long paramLong);

    public  Long incr(String paramString);

    public  Long llen(String paramString);

    public  Long sadd(String paramString, String... paramVarArgs);

    public  Long scard(String paramString);

    public  Set<String> smember(String paramString);

    public  Boolean sismember(String paramString1, String paramString2);

    public  Long expireAt(String paramString, int paramInt);

    public  Set<String> zrange(String paramString, long paramLong1, long paramLong2);

    public  Long incrAndExpireAfterToday(String paramString);

    public  Long srem(String paramString1, String paramString2);

    public  Long zrem(String paramString1, String paramString2);

    public  Long hsetnx(String paramString1, String paramString2, String paramString3);

    public  Long hincrBy(String paramString1, String paramString2, Long paramLong);

    public  String ltrim(String paramString, Long paramLong1, Long paramLong2);

    public  String getSet(String paramString1, String paramString2);

    public  Long zrank(String paramString1, String paramString2);
}

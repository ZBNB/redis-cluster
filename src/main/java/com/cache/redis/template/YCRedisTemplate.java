package com.cache.redis.template;

import com.cache.redis.config.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @Description: 自定义redisTemplate YangBin Custom Redis Template
 * @Author: yangbin
 * @Date: 上午 11:34 2017-8-26
 */
@Component
@Slf4j
public class YCRedisTemplate {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private RedisProperties redisProperties;

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    /**
     * 设置缓存
     * @param key    缓存key
     * @param value  缓存value
     */
    public String set(String key, String value) {
        String result = jedisCluster.set(key, value);
        log.debug("YCRedisTemplate:set cache key={},value={}", key, value);
        return result;
    }

    /**
     * 设置缓存，并且自己指定过期时间
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key
     * @param value
     * @param expireTime 过期时间（秒）
     */
    public String setWithExpireTime(String prefix, String key, String value, int expireTime) {
        log.debug("YCRedisTemplate:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value,
                expireTime);
        return jedisCluster.setex(prefix + KEY_SPLIT + key, expireTime, value);
    }

    /**
     * 设置缓存，并且由配置文件指定过期时间
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key
     * @param value
     */
    public String setWithPrefix(String prefix, String key, String value) {
        int EXPIRE_SECONDS = redisProperties.getTimeout();
        String result = jedisCluster.setex(prefix + KEY_SPLIT + key, EXPIRE_SECONDS, value);
        log.debug("YCRedisTemplate:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value,
                EXPIRE_SECONDS);
        return result;
    }

    /**
     * 获取指定key的缓存
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key
     */
    public String getWithPrefix(String prefix, String key) {
        String result = jedisCluster.get(prefix + KEY_SPLIT + key);
        log.debug("YCRedisTemplate:get cache key={},value={}", prefix + KEY_SPLIT + key, result);
        return result;
    }

    /**
     * 删除指定key的缓存
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key
     */
    public void deleteWithPrefix(String prefix, String key) {
        jedisCluster.del(prefix + KEY_SPLIT + key);
        log.debug("YCRedisTemplate:delete cache key={}", prefix + KEY_SPLIT + key);
    }

    public void delete(String key) {
        jedisCluster.del(key);
        log.debug("YCRedisTemplate:delete cache key={}", key);
    }
}

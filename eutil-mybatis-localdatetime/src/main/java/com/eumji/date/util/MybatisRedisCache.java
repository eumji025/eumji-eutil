package com.eumji.date.util;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-13
 * @time: 下午10:25
 */
public class MybatisRedisCache implements Cache {
    private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    private volatile RedisTemplate<Object,Object> redisTemplate;
    private String cacheId;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public MybatisRedisCache(String id){
        cacheId = id;
    }

    @Override
    public String getId() {
        return cacheId;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("save key[{}],value[{}] into redis success",key,value);
    }

    @Override
    public Object getObject(Object key) {
        if (redisTemplate == null){
            synchronized (MybatisRedisCache.class) {
                if (redisTemplate == null) {
                    redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
                }
            }
        }
        Object value = redisTemplate.opsForValue().get(key);
        logger.info("get key[{}] cache from redis is:[{}]",key,value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        Object value = redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        logger.info("remove key[{}] cache from redis success",key);
        return value;
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback) redisConnection -> {
            redisConnection.flushDb();
            return "ok";
        });
    }

    @Override
    public int getSize() {
        return  redisTemplate.execute((RedisCallback<Integer>) redisConnection -> Math.toIntExact(redisConnection.dbSize()));
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}

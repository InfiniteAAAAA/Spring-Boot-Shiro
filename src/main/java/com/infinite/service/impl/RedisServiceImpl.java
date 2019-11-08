package com.infinite.service.impl;

import com.infinite.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public boolean set(String key, Object value, long seconds) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("RedisTemplateUtil.set error: ", e);
        }
        return result;
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
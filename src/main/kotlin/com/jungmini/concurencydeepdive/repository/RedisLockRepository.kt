package com.jungmini.concurencydeepdive.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisLockRepository (
    val redisTemplate: RedisTemplate<String, String>
) {

    fun lock(key: Long): Boolean {
        return redisTemplate
            .opsForValue()
            .setIfAbsent(key.toString(), "lock", Duration.ofMillis(3000)) ?: false
    }

    fun unLock(key: Long): Boolean {
        return redisTemplate.delete(key.toString())
    }
}
package com.jungmini.concurencydeepdive.common

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LocalRedisConfig {

    @Value("\${spring.data.redis.host}")
    private lateinit var host: String

    @Value("\${spring.data.redis.port}")
    private lateinit var port: String

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
//        println("-- ${"redis://${host}:${port}"} --")
        config.useSingleServer().address = "redis://${host}:${port}"
        return Redisson.create(config)
    }
}
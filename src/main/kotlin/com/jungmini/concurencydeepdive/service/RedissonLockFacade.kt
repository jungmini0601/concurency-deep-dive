package com.jungmini.concurencydeepdive.service

import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedissonLockFacade (
    val redissonClient: RedissonClient,
    val stockService: StockService
){

    fun decrease(id: Long, quantity: Long) {
        val lock = redissonClient.getLock(id.toString())

        try {
            val available = lock.tryLock(5, 1, TimeUnit.SECONDS)

            if (!available) {
                println("current thread:${Thread.currentThread()} Lock fail")
                return
            }

            stockService.decrease(id, quantity)
        } finally {
            lock.unlock()
        }
    }
}
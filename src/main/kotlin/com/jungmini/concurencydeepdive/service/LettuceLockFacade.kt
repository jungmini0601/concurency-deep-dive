package com.jungmini.concurencydeepdive.service

import com.jungmini.concurencydeepdive.repository.RedisLockRepository
import org.springframework.stereotype.Service

@Service
class LettuceLockFacade(
    val redisLockRepository: RedisLockRepository,
    val stockService: StockService
) {
    fun decrease(id: Long, quantity: Long) {
        // 락을 획득할 때 까지 while
        while (!redisLockRepository.lock(id)) {
            Thread.sleep(1000)
        }

        try {
            stockService.decrease(id, quantity)
        } finally {
            redisLockRepository.unLock(id)
        }
    }

}
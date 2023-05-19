package com.jungmini.concurencydeepdive.service

import com.jungmini.concurencydeepdive.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class NamedLockFacade(
    val stockService: StockService,
    val stockRepository: StockRepository
) {

    fun decrease(id: Long, quantity: Long) {
        try {
            stockRepository.stockLock("LOCK:${id}")
            println("잠금획득${Thread.currentThread()}")
            stockService.decrease(id, 1)
        } catch (e: Exception) {
            println("혹시 예외가 터지나?")
        } finally {
            stockRepository.stockFreeLock("LOCK:${id}")
            println("잠금해제${Thread.currentThread()}")

        }
    }
}
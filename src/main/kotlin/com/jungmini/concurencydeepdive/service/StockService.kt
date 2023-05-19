package com.jungmini.concurencydeepdive.service

import com.jungmini.concurencydeepdive.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class StockService (
    val stockRepository: StockRepository,
) {

    @Transactional
    fun decrease(id: Long, quantity: Long) {
        try {
            val stock = stockRepository.findByWithOptimisticLock(id)
            println("quantity: ${stock.quantity}")
            stock.decrease(quantity)
            stockRepository.saveAndFlush(stock)
            println("decreased quantity: ${stock.quantity}")
        } catch (e: Exception) {
            // 충돌이 발생했을 때 로직 작성
        }

    }
}
package com.jungmini.concurencydeepdive.service

import com.jungmini.concurencydeepdive.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService (
    val stockRepository: StockRepository
) {

//    @Transactional
    @Synchronized fun decrease(id: Long, quantity: Long) {
        val stock = stockRepository.findById(id).orElseThrow {
            RuntimeException("Not Found Stock")
        }

        stock.decrease(quantity)
        stockRepository.saveAndFlush(stock)
    }
}
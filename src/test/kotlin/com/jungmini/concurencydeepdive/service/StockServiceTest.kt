package com.jungmini.concurencydeepdive.service

import com.jungmini.concurencydeepdive.domain.Stock
import com.jungmini.concurencydeepdive.repository.StockRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.assertj.core.api.Assertions.assertThat
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // ?
class StockServiceTest (
    val stockService: StockService,
    val stockRepository: StockRepository,
    val namedLockFacade: NamedLockFacade
){

    @BeforeEach
    fun `재고 데이터 생성` () {
        val stock = Stock(productId = 1L, quantity = 100L, version = 0)
        stockRepository.save(stock)
    }

    @Test
    fun `동시에 100 개의 요청이 들어간다! 낙관적 락 `() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)

        // 다른 스레드에서 수행 완료될 때까지 대기할 수 있도록 하는 API
        val latch = CountDownLatch(threadCount)

        for(i in 1..threadCount) {
            executorService.submit{
                try {
                    stockService.decrease(1L, 1L)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val stock = stockRepository.findById(1L).orElseThrow()
        assertThat(stock.quantity).isEqualTo(0)
    }
}
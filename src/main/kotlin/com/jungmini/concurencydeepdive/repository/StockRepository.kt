package com.jungmini.concurencydeepdive.repository

import com.jungmini.concurencydeepdive.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface StockRepository: JpaRepository<Stock, Long> {

    @Query("SELECT GET_LOCK(:id, 3000) ", nativeQuery = true)
    fun stockLock(id: String): Unit

    @Query("SELECT release_lock(:id)", nativeQuery = true)
    fun stockFreeLock(id: String): Unit
}
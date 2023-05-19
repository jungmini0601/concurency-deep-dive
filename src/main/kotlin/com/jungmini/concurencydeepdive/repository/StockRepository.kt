package com.jungmini.concurencydeepdive.repository

import com.jungmini.concurencydeepdive.domain.Stock
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface StockRepository: JpaRepository<Stock, Long> {

    @Query("SELECT GET_LOCK(:id, 3000) ", nativeQuery = true)
    fun stockLock(id: String): Unit

    @Query("SELECT release_lock(:id)", nativeQuery = true)
    fun stockFreeLock(id: String): Unit

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    fun findByWithPessimisticLock(id: Long): Stock
}
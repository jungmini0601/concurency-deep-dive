package com.jungmini.concurencydeepdive.repository

import com.jungmini.concurencydeepdive.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<Stock, Long>
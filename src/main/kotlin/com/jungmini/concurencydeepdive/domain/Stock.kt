package com.jungmini.concurencydeepdive.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Version
import java.lang.RuntimeException

@Entity
class Stock (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val productId: Long,
    var quantity: Long,
    @Version var version: Long
) {
    fun decrease(quantity: Long): Unit {
        if (this.quantity - quantity < 0) {
            throw RuntimeException("재고가 부족 합니당")
        }

        this.quantity = this.quantity - quantity
    }
}
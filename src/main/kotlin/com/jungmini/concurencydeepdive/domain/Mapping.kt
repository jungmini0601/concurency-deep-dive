package com.jungmini.concurencydeepdive.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Mapping (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
    val senderId: Long,
    val receiverId: Long,
    val approval: Boolean
): BaseEntity() {

    override fun toString(): String {
        return "senderId = ${senderId}, reciverId = ${receiverId}, approval = ${approval}"
    }
}
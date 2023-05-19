package com.jungmini.concurencydeepdive.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
class BaseEntity {

    @CreatedDate
    @Column(name = "created_datetime", insertable = false, updatable = false)
    lateinit var createdDate: LocalDateTime // 추후에 값이 들어오기 때문에 지연초기화

    @LastModifiedDate
    @Column(name = "updated_datetime", insertable = false, updatable = false)
    lateinit var updatedDateTime: LocalDateTime
}
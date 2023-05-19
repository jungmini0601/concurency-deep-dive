package com.jungmini.concurencydeepdive.repository

import com.jungmini.concurencydeepdive.domain.Mapping
import org.springframework.data.jpa.repository.JpaRepository

interface MappingRepository: JpaRepository<Mapping, Long>
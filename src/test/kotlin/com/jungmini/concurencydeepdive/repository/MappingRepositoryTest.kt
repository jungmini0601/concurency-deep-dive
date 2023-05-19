package com.jungmini.concurencydeepdive.repository

import com.jungmini.concurencydeepdive.domain.Mapping
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // ?
class MappingRepositoryTest (
    val mappingRepository: MappingRepository
){

    @Test
    fun `mapping 테스트`() {
        // when
        val count = mappingRepository.count()
        // then
        assertThat(count).isEqualTo(0)
    }

    @Test
    fun `insert 테스트`() {
        // when
        val mapping = Mapping(senderId = 1L, receiverId = 2L, approval = true)
        // then
        mappingRepository.save(mapping)
        println(mapping)
    }
}
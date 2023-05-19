package com.jungmini.concurencydeepdive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConcurencyDeepDiveApplication

fun main(args: Array<String>) {
    runApplication<ConcurencyDeepDiveApplication>(*args)
}

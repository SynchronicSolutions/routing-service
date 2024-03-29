package com.singidunum.authserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RoutingServiceApplication

fun main(args: Array<String>) {
	runApplication<RoutingServiceApplication>(*args)
}

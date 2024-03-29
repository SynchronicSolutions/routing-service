package com.singidunum.authserver.controllers

import com.singidunum.authserver.services.RoutingService
import kotlinx.serialization.json.JsonObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(
    private val routingService: RoutingService
) {
    @PostMapping("register")
    fun routeRegister(@RequestBody body: JsonObject): JsonObject =
        routingService.routeRegister(body)
}
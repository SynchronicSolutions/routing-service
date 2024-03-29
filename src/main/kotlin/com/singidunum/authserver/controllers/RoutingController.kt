package com.singidunum.authserver.controllers

import com.singidunum.authserver.services.RoutingService
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.json.JsonElement
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RoutingController(
    private val routingService: RoutingService
) {
    @GetMapping(value = ["/**"])
    fun routeGetMethod(request: HttpServletRequest): JsonElement =
        routingService.routeGet(request)

    @PostMapping(value = ["/**"])
    fun routePostMethod(request: HttpServletRequest, @RequestBody body: JsonElement): JsonElement =
        routingService.routePost(request, body)

    @PutMapping(value = ["/**"])
    fun routePutMethod(request: HttpServletRequest, @RequestBody body: JsonElement): JsonElement =
        routingService.routePut(request, body)

    @DeleteMapping(value = ["/**"])
    fun routeDeleteMethod(request: HttpServletRequest): JsonElement =
        routingService.routeDelete(request)
}
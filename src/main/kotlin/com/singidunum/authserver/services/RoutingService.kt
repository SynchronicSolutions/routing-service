package com.singidunum.authserver.services

import com.singidunum.authserver.networking.RoutingApiCallException
import com.singidunum.authserver.networking.routing.RoutingApiService
import com.singidunum.authserver.networking.RoutingEndpointException
import com.singidunum.authserver.networking.auth.AuthApiService
import com.singidunum.authserver.util.RequestUriUtil.getServiceName
import com.singidunum.authserver.util.RequestUriUtil.getServiceUrl
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Service
import org.springframework.http.HttpHeaders

@Service
class RoutingService(
    private val discoveryClient: DiscoveryClient,
    private val routingApiService: RoutingApiService,
    private val authApiService: AuthApiService
) {
    fun routeGet(request: HttpServletRequest): JsonElement {
        authApiService.authorize(request.getHeader(HttpHeaders.AUTHORIZATION))

        val serviceName = request.requestURI.getServiceName()
            ?: throw RoutingEndpointException("Can't find server name in Uri")

        val routingServerUrl = discoveryClient.getServiceUrl(serviceName)
            ?: throw RoutingEndpointException("Service $serviceName not available")

        return runCatching {
            routingApiService.getRouting(routingServerUrl, request.requestURI + "?" + request.queryString)
        }.onFailure {
            throw RoutingApiCallException("Found error while trying to route `GET`", it)
        }.getOrThrow()
    }

    fun routePost(request: HttpServletRequest, requestBody: JsonElement): JsonElement {
        authApiService.authorize(request.getHeader(HttpHeaders.AUTHORIZATION))

        val serviceName = request.requestURI.getServiceName()
            ?: throw RoutingEndpointException("Can't find server name in Uri")

        val routingServerUrl = discoveryClient.getServiceUrl(serviceName)
            ?: throw RoutingEndpointException("Service $serviceName not available")

        return runCatching {
            routingApiService.postRouting(
                baseUrl = routingServerUrl,
                path = request.requestURI,
                body = requestBody
            )
        }.onFailure {
            throw RoutingApiCallException("Found error while trying to route `POST`", it)
        }.getOrThrow()
    }

    fun routePut(request: HttpServletRequest, requestBody: JsonElement): JsonElement {
        authApiService.authorize(request.getHeader(HttpHeaders.AUTHORIZATION))

        val serviceName = request.requestURI.getServiceName()
            ?: throw RoutingEndpointException("Can't find server name in Uri")

        val routingServerUrl = discoveryClient.getServiceUrl(serviceName)
            ?: throw RoutingEndpointException("Service $serviceName not available")

        return runCatching {
            routingApiService.putRouting(
                baseUrl = routingServerUrl,
                path = request.requestURI,
                body = requestBody
            )
        }.onFailure {
            throw RoutingApiCallException("Found error while trying to route `GET`", it)
        }.getOrThrow()
    }

    fun routeDelete(request: HttpServletRequest): JsonElement {
        authApiService.authorize(request.getHeader(HttpHeaders.AUTHORIZATION))

        val serviceName = request.requestURI.getServiceName()
            ?: throw RoutingEndpointException("Can't find server name in Uri")

        val routingServerUrl = discoveryClient.getServiceUrl(serviceName)
            ?: throw RoutingEndpointException("Service $serviceName not available")

        return runCatching {
            routingApiService.deleteRouting(routingServerUrl, request.requestURI)
        }.onFailure {
            throw RoutingApiCallException("Found error while trying to route `GET`", it)
        }.getOrThrow()
    }

    fun routeRegister(requestBody: JsonObject): JsonObject =
        authApiService.register(requestBody)

    fun getServiceAddress(serviceName: String): ServiceInstance? =
        discoveryClient
            .getInstances("auth-service")
            .firstOrNull()
}
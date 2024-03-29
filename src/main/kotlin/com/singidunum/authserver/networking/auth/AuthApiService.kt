package com.singidunum.authserver.networking.auth

import com.singidunum.authserver.networking.ApiService
import com.singidunum.authserver.networking.AuthorizationException
import com.singidunum.authserver.networking.RoutingEndpointException
import com.singidunum.authserver.networking.create
import com.singidunum.authserver.util.RequestUriUtil.getServiceUrl
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

private const val AUTH_SERVICE_URL = "auth-service"

@Service
class AuthApiService(
    private val discoveryClient: DiscoveryClient
): ApiService() {
    val authEndpoints: AuthEndpoints by lazy {
        create(getAuthServiceUrl())
    }

    fun authorize(authorizationHeader: String): JsonElement {
        val response = authEndpoints
            .authorize(authorizationHeader)
            .execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw AuthorizationException("Routing request to auth service failed")
        }

        return responseBody
    }

    fun register(userObject: JsonObject): JsonObject {
        val response = authEndpoints
            .register(userObject)
            .execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw AuthorizationException("Routing request to auth service failed")
        }

        return responseBody
    }

    private fun getAuthServiceUrl() = discoveryClient.getServiceUrl(AUTH_SERVICE_URL)
        ?: throw RoutingEndpointException("Service $AUTH_SERVICE_URL not available")
}
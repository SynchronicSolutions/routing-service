package com.singidunum.authserver.networking.routing

import com.singidunum.authserver.networking.ApiService
import com.singidunum.authserver.networking.create
import kotlinx.serialization.json.JsonElement
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class RoutingApiService: ApiService() {
    fun getRouting(baseUrl: String, path: String): JsonElement {
        val routingEndpoints: RoutingEndpoints = create(baseUrl)

        val response = routingEndpoints.getRouting(path).execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw IllegalStateException("Routing request to $baseUrl/$path failed")
        }

        return responseBody
    }

    fun postRouting(baseUrl: String, path: String, body: JsonElement): JsonElement {
        val routingEndpoints: RoutingEndpoints = create(baseUrl)

        val response = routingEndpoints.postRouting(path, body).execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw IllegalStateException("Routing request to $baseUrl/$path failed")
        }

        return responseBody
    }

    fun putRouting(baseUrl: String, path: String, body: JsonElement): JsonElement {
        val routingEndpoints: RoutingEndpoints = create(baseUrl)

        val response = routingEndpoints.putRouting(path, body).execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw IllegalStateException("Routing request to $baseUrl/$path failed")
        }

        return responseBody
    }

    fun deleteRouting(baseUrl: String, path: String): JsonElement {
        val routingEndpoints: RoutingEndpoints = create(baseUrl)

        val response = routingEndpoints.deleteRouting(path).execute()

        val responseBody = response.body()

        if (response.errorBody() != null || responseBody == null) {
            throw IllegalStateException("Routing request to $baseUrl/$path failed")
        }

        return responseBody
    }
}
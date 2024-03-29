package com.singidunum.authserver.networking.auth

import com.singidunum.authserver.networking.EndPoints
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.springframework.http.HttpHeaders
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthEndpoints: EndPoints {
    @GET("/api/authorize")
    fun authorize(
        @Header(HttpHeaders.AUTHORIZATION) authorization: String
    ): Call<JsonElement>

    @POST("/api/register")
    fun register(
        @Body userBody: JsonObject
    ): Call<JsonObject>
}
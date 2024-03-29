package com.singidunum.authserver.networking.routing

import com.singidunum.authserver.networking.EndPoints
import kotlinx.serialization.json.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface RoutingEndpoints: EndPoints {
    @GET
    fun getRouting(
        @Url url: String
    ): Call<JsonElement>

    @POST
    fun postRouting(
        @Url url: String,
        @Body body: JsonElement
    ): Call<JsonElement>

    @PUT
    fun putRouting(
        @Url url: String,
        @Body body: JsonElement
    ): Call<JsonElement>

    @DELETE
    fun deleteRouting(
        @Url url: String
    ): Call<JsonElement>
}
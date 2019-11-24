package com.example.viacinema.usecase

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("facts")
    fun getCatFacts(@Query("limit") limit: Int): Call<ApiResponse>
}
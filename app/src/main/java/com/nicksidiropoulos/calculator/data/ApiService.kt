package com.nicksidiropoulos.calculator.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest")
    fun convertAmount(
        @Query("amount") amount: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): Call<ConvertionResult>
}
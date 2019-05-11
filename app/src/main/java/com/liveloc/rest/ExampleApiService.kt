package com.liveloc.rest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExampleApiService {
    //https://api.predic8.de/shop/products/62
    @GET("shop/products/62")
    fun getProduct() : Call<Example>

    companion object Factory {
        val BASE_URL = "https://api.predic8.de/"
        fun create(): ExampleApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ExampleApiService::class.java)
        }
    }
}
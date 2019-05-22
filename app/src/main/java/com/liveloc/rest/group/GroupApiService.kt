package com.liveloc.rest.group

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GroupApiService {
    //https://api.predic8.de/shop/products/62
    //@GET("api/groups/{group_id}")
    @GET("groups/1")
    fun getGroup() : Call<GroupDTO>

    @POST("groups/")
    fun createGroup(@Body groupDTO: GroupDTO) : Call<GroupDTO>

    companion object Factory {
        val BASE_URL = "https://liveloc.free.beeceptor.com/api/"
        fun create(): GroupApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GroupApiService::class.java)
        }
    }
}
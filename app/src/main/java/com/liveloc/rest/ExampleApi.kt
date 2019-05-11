package com.liveloc.rest

import android.util.Log
import android.widget.Toast
import com.liveloc.BuildConfig
import com.liveloc.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

class ExampleApi {
    init {
        callWebService()
    }

    fun callWebService() {
        val apiService = ExampleApiService.create()
        val call = apiService.getProduct()
        Log.d("REQUEST", call.toString() + "")
        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: retrofit2.Response<Example>?) {
                if (response != null) {
                    //var list: List<Example> = response.body().categories!!
                    var example: Example? = response.body()
                    Log.d("REQUEST", call.toString() + "")
                    Log.d("REQUEST", example.toString() + "")
                    //Toast.makeText(MainActivity, "List of Category  \n  " + msg, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
            }
        })
    }
}
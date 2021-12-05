package com.example.truecreditslist.api

import android.util.Log
import com.example.truecreditslist.data.TruecreditsPost
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

abstract class TruecreditsApi {

    @GET("v2/beers")
    abstract suspend fun getListData(
        @Query("page") limit: Int,
        @Query("per_page") after: String? = null,
    ): Call<List<TruecreditsPost>>

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/"
        fun create(): TruecreditsApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("API", it) })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL)!!)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TruecreditsApi::class.java)
        }
    }

}
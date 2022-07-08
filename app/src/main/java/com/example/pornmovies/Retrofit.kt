package com.example.pornmovies

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Retrofit {
    companion object{
        val url = "https://api.opendota.com/api/"
        val baseImg = "https://api.opendota.com"
        fun getRetrofitInstance(): RetrofitApi {
            fun getOkHttpInstance():OkHttpClient{
                var logging = HttpLoggingInterceptor()
                logging.level =HttpLoggingInterceptor.Level.BODY
                return OkHttpClient.Builder().addInterceptor(logging).build()
            }
            var retrofit = Retrofit.Builder()
                .client(getOkHttpInstance())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
            return retrofit

        }
    }
}
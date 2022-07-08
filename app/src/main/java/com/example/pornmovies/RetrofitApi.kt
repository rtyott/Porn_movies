package com.example.pornmovies

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("heroStats")
    suspend fun getHeroesStats():Response<List<HeroListEntity>>
}
package com.example.tima

import com.example.tima.models.Characters
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: Int
    ): Characters
}

object Retrofit {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

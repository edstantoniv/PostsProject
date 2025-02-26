package com.example.postsproject.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: PostsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsApi::class.java)
    }
}
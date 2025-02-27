package com.example.postsproject.model

import retrofit2.Response

class PostRepository {
    // Function to fetch posts from the API using PostRetrofitClient
    suspend fun getPosts(): Response<List<Post>> {
        return PostRetrofitClient.api.getPosts()
    }
}
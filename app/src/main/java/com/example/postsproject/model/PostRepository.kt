package com.example.postsproject.model

class PostRepository {
    // Function to fetch posts from the API using PostRetrofitClient
    private var data : List<Post>? = null
    private var errorMsg : String? = null

    // Should probably have some retry count
    suspend fun getPosts(): List<Post>? {
            if (data == null){
                data = retrievePosts()
            }
        return data
    }

    suspend fun retrievePosts() : List<Post>?
    {
        val response = PostRetrofitClient.api.getPosts()
        if (!response.isSuccessful)
            errorMsg = response.message()
        return response.body()
    }

    fun getErrorMsg() : String?
    {
        return errorMsg
    }
}
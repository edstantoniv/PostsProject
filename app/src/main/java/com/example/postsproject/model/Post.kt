package com.example.postsproject.model

/* Expected contents of list items returned from an @GET call to
    https://jsonplaceholder.typicode.com/posts#
 */
data class Post(
    val userId : Int,
    val id: Int,
    val title: String,
    val body: String)

package com.example.postsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsproject.model.Post
import com.example.postsproject.model.PostRetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel: ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response: Response<List<Post>> = PostRetrofitClient.api.getPosts()
                if (response.isSuccessful) {
                    _posts.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}
package com.example.postsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsproject.model.Post
import com.example.postsproject.model.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchPosts() {
        if (_posts.value != null) {
            return
        }
        viewModelScope.launch {
            try {
                // Call the repository to fetch posts
                val response: Response<List<Post>> = repository.getPosts()
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

    //Useful when we extend and have a background task that is checking for data updates
    fun updatePosts(newPosts: List<Post>)
    {
        _posts.value = newPosts

    }
    fun updateError(newErrorMessage: String)
    {
        _errorMessage.value = newErrorMessage
    }

}
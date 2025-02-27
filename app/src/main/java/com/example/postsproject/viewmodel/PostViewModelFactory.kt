package com.example.postsproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postsproject.model.PostRepository

class PostViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    // Correct method signature with proper type bounds
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure that we are creating an instance of PostViewModel
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
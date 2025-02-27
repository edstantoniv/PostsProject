package com.example.postsproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsproject.viewmodel.PostViewModel
import com.example.postsproject.model.PostRepository
import com.example.postsproject.viewmodel.PostViewModelFactory
import com.example.postsproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Create an instance of PostRepository
    private val repository = PostRepository()
    // Declare the binding object
    private lateinit var binding: ActivityMainBinding
    // Instantiate the ViewModel with the repository
    private val viewModel: PostViewModel by viewModels {
        PostViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        var postAdapter = PostAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postAdapter

        // Fetch posts when the activity starts
        viewModel.fetchPosts()

        // Observe LiveData from ViewModel
        viewModel.posts.observe(this) { posts ->
            postAdapter = PostAdapter(posts)
            binding.recyclerView.adapter = postAdapter
        }

        // Handle error messages
        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}
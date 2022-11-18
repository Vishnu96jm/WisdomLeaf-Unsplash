package com.wisdomleaf.unsplash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.wisdomleaf.unsplash.App
import com.wisdomleaf.unsplash.R
import com.wisdomleaf.unsplash.databinding.ActivityMainBinding
import com.wisdomleaf.unsplash.repo.UnsplashPagingSource
import com.wisdomleaf.unsplash.repo.UnsplashRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val unsplashAdapter = UnsplashAdapter()

    //lazy keyword -> initialization will occur only after the first call.
    private val unsplashViewModel: UnsplashViewModel by lazy {
        ViewModelProvider(this).get(UnsplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViews()
        fetchImages()
    }

    /*
    * This code fetches the images from UnsplashViewModel.
    * Since the ViewModel returns a Flow, use collectLatest to access the stream.
    * Send the list to the adapter by calling submitData.*/
    private fun fetchImages() {
        lifecycleScope.launch {
            unsplashViewModel.fetchImages().collectLatest { pagingData ->
                unsplashAdapter.submitData(pagingData)
            }
        }

    }

    private fun setupViews() {
        binding.rvItems.adapter = unsplashAdapter
    }
}
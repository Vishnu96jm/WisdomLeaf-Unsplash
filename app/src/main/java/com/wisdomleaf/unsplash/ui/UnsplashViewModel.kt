package com.wisdomleaf.unsplash.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisdomleaf.unsplash.App
import com.wisdomleaf.unsplash.model.UnsplashItem
import com.wisdomleaf.unsplash.repo.UnsplashRepo
import kotlinx.coroutines.flow.Flow

class UnsplashViewModel (application: Application) : AndroidViewModel(application) {
    private val remoteApiService = App.apiService

    private val unsplashRepo = UnsplashRepo(remoteApiService)

    fun fetchImages(): Flow<PagingData<UnsplashItem>> {
        //cachedIn call to cache the data in viewModelScope.
        return unsplashRepo.fetchImages().cachedIn(viewModelScope)
    }
}

package com.wisdomleaf.unsplash.repo

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisdomleaf.unsplash.App
import com.wisdomleaf.unsplash.db.UnsplashDatabase
import com.wisdomleaf.unsplash.model.UnsplashItem
import com.wisdomleaf.unsplash.networking.RemoteApiService

import kotlinx.coroutines.flow.Flow

const val LATENT_PAGE_SIZE = 30

//Passing in RemoteApiService reference to download list of posts from the Reddit API
class UnsplashRepo(private val remoteApiService: RemoteApiService) {

    fun fetchImages(): Flow<PagingData<UnsplashItem>> {
        //return an instance of Pager class, which is used to fetch a stream of data from PagingSource.
        //Flow from Kotlin coroutines as a return type
        return Pager(
            PagingConfig(pageSize = LATENT_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UnsplashPagingSource(remoteApiService) }
        ).flow

    }
}


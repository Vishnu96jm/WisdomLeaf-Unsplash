package com.wisdomleaf.unsplash.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wisdomleaf.unsplash.db.UnsplashDatabase
import com.wisdomleaf.unsplash.model.UnsplashItem
import com.wisdomleaf.unsplash.networking.RemoteApiService
import retrofit2.HttpException
import java.io.IOException


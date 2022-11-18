package com.wisdomleaf.unsplash.networking

import com.wisdomleaf.unsplash.model.UnsplashItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("v2/list?page=2&limit=20")
    suspend fun fetchImages(
        @Query("page") page: Int
    ): Response<List<UnsplashItem>>
}
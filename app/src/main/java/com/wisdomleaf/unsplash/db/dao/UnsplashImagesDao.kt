package com.wisdomleaf.unsplash.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wisdomleaf.unsplash.model.UnsplashItem

@Dao
interface UnsplashImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveImages(unsplashItems: List<UnsplashItem>)

    @Query("SELECT * FROM unsplashItem")
    fun getImages(): PagingSource<Int, UnsplashItem>
}
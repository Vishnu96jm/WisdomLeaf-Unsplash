package com.wisdomleaf.unsplash.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "unsplashItem")
data class UnsplashItem(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("downloadUrl")
    val downloadUrl: String,
    @SerializedName("url")
    val url: String
)



package com.wisdomleaf.unsplash.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wisdomleaf.unsplash.db.dao.UnsplashImagesDao
import com.wisdomleaf.unsplash.model.UnsplashItem


@Database(
    entities = [UnsplashItem::class],
    version = 1,
    exportSchema = false
)
abstract class UnsplashDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): UnsplashDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, UnsplashDatabase::class.java, "unsplashImage.db")
            return databaseBuilder.build()
        }
    }

    abstract fun unsplashImagesDao(): UnsplashImagesDao
}
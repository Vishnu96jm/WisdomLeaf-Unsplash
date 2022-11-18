package com.wisdomleaf.unsplash

import android.app.Application
import com.wisdomleaf.unsplash.networking.buildApiService

class App : Application() {

    companion object {
        private lateinit var instance: App

        //To expose the RemoteApi to the rest of the app
         val apiService by lazy { buildApiService() }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
package com.example.httpjsonparser

import android.app.Application

class JsonParserApp: Application() {

    lateinit var httpManager: HttpManager
    lateinit var likesManager: LikesManager

    override fun onCreate() {
        super.onCreate()
        httpManager = HttpManager(this)
        likesManager = LikesManager()
    }
}
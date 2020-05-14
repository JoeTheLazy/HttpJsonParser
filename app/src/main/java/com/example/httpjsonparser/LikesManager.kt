package com.example.httpjsonparser

class LikesManager {
    private var likesCount: Int = 0

    fun incrementLikes() {
        likesCount++
    }

    fun getLikes(): Int {
        return likesCount
    }
}
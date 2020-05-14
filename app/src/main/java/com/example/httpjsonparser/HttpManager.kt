package com.example.httpjsonparser

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class HttpManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getUserInfo(onUserReady: (UserInfo) -> Unit, onError: ((Int) -> Unit)? = null) {
        val userURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json"

        val request = StringRequest(
            Request.Method.GET, userURL,
            { response ->
                // Success
                val gson = Gson()
                val userInfo = gson.fromJson(response, UserInfo::class.java )

                onUserReady(userInfo)

            },
            { error ->
                // Failure
                onError?.invoke(error.networkResponse.statusCode)
            }
        )

        queue.add(request)
    }
}
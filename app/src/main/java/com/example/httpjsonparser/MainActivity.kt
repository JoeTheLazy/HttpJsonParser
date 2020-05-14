package com.example.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var httpManager: HttpManager
    lateinit var likesManager: LikesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpManager = (application as JsonParserApp).httpManager
        likesManager = (application as JsonParserApp).likesManager

        fetch_button.setOnClickListener{
            fetchUserInfo()
        }
    }

    private fun fetchUserInfo() {
        httpManager.getUserInfo (
            { userInfo ->
                toastMessage(userInfo.username)
            },
            {
                toastMessage("Error! Something went wrong!")
            }
        )
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

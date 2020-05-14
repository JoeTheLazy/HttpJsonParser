package com.example.httpjsonparser

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.httpjsonparser.R.id.fetch_button
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var httpManager: HttpManager
    lateinit var likesManager: LikesManager
    lateinit var fetchButton: Button
    lateinit var profilePic: ImageView
    lateinit var platformText: TextView
    lateinit var userName: TextView
    lateinit var names: TextView
    lateinit var noseText: TextView
    lateinit var likesText: TextView
    lateinit var likeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpManager = (application as JsonParserApp).httpManager
        likesManager = (application as JsonParserApp).likesManager

        fetchButton = findViewById(R.id.fetch_button)
        likeButton = findViewById(R.id.like_button)

        userName = findViewById(R.id.user_name)
        platformText = findViewById(R.id.platform_text)
        profilePic = findViewById(R.id.profile_pic)
        names = findViewById(R.id.names_text)
        noseText = findViewById(R.id.nose_text)
        likesText = findViewById(R.id.likes_text)

        fetchButton.setOnClickListener{
            fetchUserInfo()
        }

        likeButton.setOnClickListener{
            likeButtonClicked()
        }
    }

    private fun fetchUserInfo() {
        httpManager.getUserInfo (
            { userInfo ->
                switchToProfileView()
                loadProfileText(userInfo)
                loadProfilePic(userInfo.profilePicURL)
            },
            {
                toastMessage("Error! Something went wrong!")
            }
        )
    }

    private fun switchToProfileView() {
        fetchButton.visibility = View.GONE

        userName.visibility = View.VISIBLE
        platformText.visibility = View.VISIBLE
        profilePic.visibility = View.VISIBLE
        names.visibility = View.VISIBLE
        noseText.visibility = View.VISIBLE
        likesText.visibility = View.VISIBLE
        likeButton.visibility = View.VISIBLE
    }

    private fun loadProfileText(userInfo: UserInfo) {
        userName.text = userInfo.username
        platformText.text = userInfo.platform.toString()
        names.text = "${userInfo.firstName} ${userInfo.lastName}"
        noseText.text = "Has nose?: ${userInfo.hasNose}"
    }

    private fun loadProfilePic(url: String) {
        Picasso.get().load(url).into(profilePic)
    }

    private fun likeButtonClicked() {
        likesManager.incrementLikes()
        likesText.text = "Likes: ${likesManager.getLikes()}"
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

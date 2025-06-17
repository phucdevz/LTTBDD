package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Load ảnh từ URL vào profileImage
        val imageUrl = "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/06/anh-itachi-2.jpg"
        val profileImage = findViewById<android.widget.ImageView>(R.id.profileImage)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.profile_placeholder)
            .error(R.drawable.profile_placeholder)
            .circleCrop()
            .into(profileImage)

        // Xử lý nút back
        findViewById<CardView>(R.id.backButton).setOnClickListener {
            finish()
        }

        // Xử lý nút email validation
        findViewById<CardView>(R.id.emailValidationButton).setOnClickListener {
            val intent = Intent(this, EmailValidationActivity::class.java)
            startActivity(intent)
        }
    }
} 
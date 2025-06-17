package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Ẩn thanh hành động
        supportActionBar?.hide()

        // Khởi tạo các view
        val backButton = findViewById<CardView>(R.id.backButton)
        val editButton = findViewById<CardView>(R.id.editButton)
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvLocation = findViewById<TextView>(R.id.tvLocation)

        // Xử lý sự kiện khi nhấn nút quay lại
        backButton.setOnClickListener {
            finish() // Đóng activity hiện tại
        }

        // Xử lý sự kiện khi nhấn nút chỉnh sửa
        editButton.setOnClickListener {
            Toast.makeText(this, "Chỉnh sửa hồ sơ", Toast.LENGTH_SHORT).show()
            // Code mở màn hình chỉnh sửa
        }
    }
}

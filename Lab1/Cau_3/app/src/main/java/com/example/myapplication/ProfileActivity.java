package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ẩn thanh hành động
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Khởi tạo các view
        CardView backButton = findViewById(R.id.backButton);
        CardView editButton = findViewById(R.id.editButton);
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvLocation = findViewById(R.id.tvLocation);

        // Xử lý sự kiện khi nhấn nút quay lại
        backButton.setOnClickListener(v -> {
            finish(); // Đóng activity hiện tại
        });

        // Xử lý sự kiện khi nhấn nút chỉnh sửa
        editButton.setOnClickListener(v -> {
            Toast.makeText(this, "Chỉnh sửa hồ sơ", Toast.LENGTH_SHORT).show();
            // Code mở màn hình chỉnh sửa
        });
    }
}

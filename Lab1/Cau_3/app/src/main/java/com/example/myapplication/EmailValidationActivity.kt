package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class EmailValidationActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var tvError: TextView
    private lateinit var btnCheck: Button

    // Pattern cho việc kiểm tra email hợp lệ
    private val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_validation)

        // Ẩn thanh action bar
        supportActionBar?.hide()

        // Ánh xạ view
        etEmail = findViewById(R.id.etEmail)
        tvError = findViewById(R.id.tvError)
        btnCheck = findViewById(R.id.btnCheck)

        // Thêm TextWatcher để kiểm tra email trong khi gõ
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Ẩn thông báo lỗi khi người dùng bắt đầu nhập lại
                tvError.visibility = View.INVISIBLE
            }
        })

        // Xử lý sự kiện khi nhấn nút Kiểm tra
        btnCheck.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                tvError.text = "Vui lòng nhập email"
                tvError.visibility = View.VISIBLE
            } else if (!isValidEmail(email)) {
                tvError.text = "Email không đúng định dạng"
                tvError.visibility = View.VISIBLE
            } else {
                // Email hợp lệ
                tvError.visibility = View.INVISIBLE
                Toast.makeText(this, "Email hợp lệ: $email", Toast.LENGTH_SHORT).show()

                // Ở đây bạn có thể thêm code để chuyển sang màn hình tiếp theo
                // hoặc xử lý logic tiếp theo của ứng dụng
            }
        }
    }

    // Hàm kiểm tra email có đúng định dạng không
    private fun isValidEmail(email: String): Boolean {
        return EMAIL_PATTERN.matcher(email).matches()
    }
}

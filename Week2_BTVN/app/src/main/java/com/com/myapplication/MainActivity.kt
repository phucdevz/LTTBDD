package com.com.myapplication

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Layout cha căn giữa cả dọc lẫn ngang
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Tiêu đề
        val title = TextView(this).apply {
            text = "THỰC HÀNH 01"
            textSize = 28f
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 60)
            setTextColor(0xFF000000.toInt()) // màu đen đậm
        }
        rootLayout.addView(title)

        // Tạo drawable bo góc cho form
        val formBackground = GradientDrawable().apply {
            setColor(0xFFF0F0F0.toInt()) // màu xám nhạt
            cornerRadius = 48f // bo góc lớn
            setStroke(2, 0xFFE0E0E0.toInt())
        }

        // Khung nhập liệu bo góc, bóng đổ
        val formLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
            background = formBackground
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(40, 0, 40, 0)
            layoutParams = params
            elevation = 16f
        }

        // Họ và tên
        val nameLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 24)
            layoutParams = params
        }
        val nameLabel = TextView(this).apply {
            text = "Họ và tên"
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER_VERTICAL
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams = params
            setTextColor(0xFF000000.toInt()) // màu đen đậm
        }
        val nameInput = EditText(this).apply {
            hint = "Nhập họ tên"
            textSize = 18f
            setPadding(24, 12, 24, 12)
            background = ContextCompat.getDrawable(context, android.R.drawable.edit_text)
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
            layoutParams = params
        }
        nameLayout.addView(nameLabel)
        nameLayout.addView(nameInput)
        formLayout.addView(nameLayout)

        // Tuổi
        val ageLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 0)
            layoutParams = params
        }
        val ageLabel = TextView(this).apply {
            text = "Tuổi"
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER_VERTICAL
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams = params
            setTextColor(0xFF000000.toInt()) // màu đen đậm
        }
        val ageInput = EditText(this).apply {
            hint = "Nhập tuổi"
            textSize = 18f
            inputType = InputType.TYPE_CLASS_NUMBER
            setPadding(24, 12, 24, 12)
            background = ContextCompat.getDrawable(context, android.R.drawable.edit_text)
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f)
            layoutParams = params
        }
        ageLayout.addView(ageLabel)
        ageLayout.addView(ageInput)
        formLayout.addView(ageLayout)

        rootLayout.addView(formLayout)

        // Nút kiểm tra lớn, bo góc, màu xanh, bóng đổ
        val buttonBackground = GradientDrawable().apply {
            setColor(0xFF2962FF.toInt()) // xanh đậm
            cornerRadius = 32f
        }
        val checkButton = Button(this).apply {
            text = "Kiểm tra"
            textSize = 20f
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            background = buttonBackground
            setPadding(0, 24, 0, 24)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(80, 60, 80, 0)
            layoutParams = params
            elevation = 8f
        }
        rootLayout.addView(checkButton)

        // TextView kết quả
        val resultText = TextView(this).apply {
            textSize = 20f
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            setPadding(0, 40, 0, 0)
        }
        rootLayout.addView(resultText)

        setContentView(rootLayout)

        // Xử lý logic khi nhấn nút
        checkButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val ageStr = ageInput.text.toString().trim()
            val age = ageStr.toIntOrNull()
            if (name.isEmpty() || ageStr.isEmpty()) {
                resultText.text = "Vui lòng nhập đầy đủ họ tên và tuổi!"
                resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                return@setOnClickListener
            }
            if (age == null) {
                resultText.text = "Tuổi phải là số hợp lệ!"
                resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                return@setOnClickListener
            }
            val result = when {
                age <= 0 -> "Tuổi phải lớn hơn 0!"
                age > 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..5 -> "Trẻ em"
                age in 1..1 -> "Em bé"
                else -> "Tuổi không hợp lệ!"
            }
            resultText.text = "$name: $result"
            resultText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
        }
    }
} 
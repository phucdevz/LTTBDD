package com.example.nav_otp

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    prefill: Triple<String, String, String>? = null
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf(prefill?.first ?: "") }
    var errorText by remember { mutableStateOf<String?>(null) }
    val showUserData = prefill?.let { it.first.isNotBlank() && it.second.isNotBlank() && it.third.isNotBlank() } ?: false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "Logo UTH",
            modifier = Modifier
                .height(180.dp)
                .width(180.dp)
                .padding(bottom = 24.dp)
        )

        Text(
            text = "SmartTasks",
            color = Color(0xFF007BFF),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Forget Password?",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            "Enter your Email, we will send you a verification code.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorText = null
            },
            label = { Text("Your Email", fontSize = 16.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            singleLine = true,
            isError = errorText != null,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorText != null) {
            Text(text = errorText ?: "", color = Color.Red, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    errorText = "Email không hợp lệ"
                } else {
                    Toast.makeText(context, "OTP đã được gửi đến $email", Toast.LENGTH_SHORT).show()
                    navController.navigate("verify_code_screen/$email")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF339CFF))
        ) {
            Text("Next", color = Color.White, fontSize = 18.sp)
        }

        if (showUserData && prefill != null) {
            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Email: ${prefill.first}", fontSize = 20.sp)
            Text("OTP: ${prefill.second}", fontSize = 20.sp)
            Text("Password: ${prefill.third}", fontSize = 20.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForgetPasswordPreview() {
    ForgetPasswordScreen(navController = rememberNavController())
}

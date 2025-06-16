package com.example.nav_otp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.nav_otp.ui.theme.Nav_OTPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nav_OTPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "forget_password_screen",
        modifier = modifier
    ) {
        // Màn hình 1: nhập email (hỗ trợ nhận lại dữ liệu sau khi Confirm)
        composable(
            route = "forget_password_screen?email={email}&otp={otp}&password={password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType; defaultValue = "" },
                navArgument("otp") { type = NavType.StringType; defaultValue = "" },
                navArgument("password") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ForgetPasswordScreen(
                navController = navController,
                prefill = Triple(email, otp, password)
            )
        }

        // Màn hình 2: nhập mã OTP
        composable(
            route = "verify_code_screen/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController = navController, email = email)
        }

        // Màn hình 3: tạo mật khẩu mới
        composable(
            route = "reset_password_screen/{email}/{otp}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("otp") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            ResetPasswordScreen(navController = navController, email = email, otp = otp)
        }

        // Màn hình 4: xác nhận và quay lại màn 1
        composable(
            route = "confirmation_screen/{email}/{otp}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("otp") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmationScreen(navController = navController, email = email, otp = otp, password = password)
        }
    }
}

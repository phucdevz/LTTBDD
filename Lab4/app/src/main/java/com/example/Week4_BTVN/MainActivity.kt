package com.example.Week4_BTVN

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.Week4_BTVN.ui.theme.Week4_BTVNTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week4_BTVNTheme {
                var showSplash by remember { mutableStateOf(true) }
                var currentPage by remember { mutableStateOf(0) }

                if (showSplash) {
                    SplashScreen {
                        showSplash = false
                        currentPage = 0  // bắt đầu vào onboarding trang 0
                    }
                } else {
                    when (currentPage) {
                        0 -> OnboardingScreen(
                            onSkip = {
                                // Bỏ qua onboarding, chuyển màn chính
                                currentPage = 3
                            },
                            onNext = {
                                currentPage = 1
                            }
                        )
                        1 -> OnboardingScreen2(
                            onBack = { currentPage = 0 },
                            onSkip = {
                                currentPage = 3
                            },
                            onNext = {
                                currentPage = 2
                            }
                        )
                        2 -> OnboardingScreen3(
                            onBack = { currentPage = 1 },
                            onSkip = {
                                currentPage = 3
                            },
                            onGetStarted = {
                                currentPage = 3
                            }
                        )
                        3 -> {
                            // Màn chính app sau khi onboarding xong
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Xin chào!", fontSize = 32.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

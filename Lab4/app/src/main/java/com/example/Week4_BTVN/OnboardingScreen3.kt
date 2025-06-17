package com.example.Week4_BTVN

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Week4_BTVN.DotsIndicator

@Composable
fun OnboardingScreen3(
    onBack: () -> Unit = {},
    onGetStarted: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    var started by remember { mutableStateOf(false) }

    if (started) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Xin chào",
                color = Color(0xFF006EE9),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Dots + Skip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DotsIndicator(totalDots = 3, selectedIndex = 2)

                Text(
                    text = "skip",
                    color = Color(0xFF006EE9),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onSkip() }
                )
            }

            // Nội dung chính
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reminder_notification),
                    contentDescription = "Reminder Notification",
                    modifier = Modifier.size(260.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Reminder Notification",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Row chứa nút Back (image) và nút Get Started
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút Back nền tròn trắng, clickable gọi onBack
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White, shape = CircleShape)
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.page3_image),
                        contentDescription = "Back Icon",
                        modifier = Modifier.size(50.dp)
                    )
                }

                // Nút Get Started bên phải
                Button(
                    onClick = {
                        started = true
                        onGetStarted()
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .width(250.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
                ) {
                    Text(text = "Get Started", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

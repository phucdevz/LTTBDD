package com.example.Week4_BTVN

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

import com.example.Week4_BTVN.DotsIndicator

@Composable
fun OnboardingScreen(
    onSkip: () -> Unit = {},
    onNext: () -> Unit = {}
) {
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
            DotsIndicator(totalDots = 3, selectedIndex = 0)

            Text(
                text = "skip",
                color = Color(0xFF006EE9),
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSkip() }
            )
        }

        // Nội dung chính trang 1
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.task_management),
                contentDescription = "Easy Time Management",
                modifier = Modifier.size(260.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Easy Time Management",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        // Nút Next dưới cùng
        Button(
            onClick = { onNext() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        ) {
            Text(text = "Next", color = Color.White, fontSize = 16.sp)
        }
    }
}

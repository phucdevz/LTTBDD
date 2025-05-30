package com.example.Week4_BTVN

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.PaddingValues
import com.example.Week4_BTVN.DotsIndicator
import androidx.compose.foundation.clickable

@Composable
fun OnboardingScreen2(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
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
            DotsIndicator(totalDots = 3, selectedIndex = 1)

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
                painter = painterResource(id = R.drawable.increase_efficiency),
                contentDescription = "Increase Work Effectiveness",
                modifier = Modifier.size(260.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Increase Work Effectiveness",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Time management and the determination of more important tasks will give your job statistics better and always improve",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        // Row chứa 2 nút Back và Next dưới cùng
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBack,
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.page3_image),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            Button(
                onClick = onNext,
                modifier = Modifier
                    .height(48.dp)
                    .width(250.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
            ) {
                Text(text = "Next", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

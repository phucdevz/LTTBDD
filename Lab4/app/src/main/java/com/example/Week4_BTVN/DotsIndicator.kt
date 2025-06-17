package com.example.Week4_BTVN

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
    Row {
        repeat(totalDots) { index ->
            val color = if (index == selectedIndex) Color(0xFF006EE9) else Color.LightGray
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(horizontal = 4.dp)
            )
            if (index != totalDots - 1) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

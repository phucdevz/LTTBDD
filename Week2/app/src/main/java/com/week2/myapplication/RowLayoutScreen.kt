package com.week2.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun RowLayoutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Row Layout", color = Color(0xFF5B9EFF), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(16.dp))
        repeat(4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFF0F4FF), RoundedCornerShape(16.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { idx ->
                    Box(
                        modifier = Modifier
                            .size(60.dp, 40.dp)
                            .background(if (idx == 1) Color(0xFF5B9EFF) else Color(0xFFD6E6FF), RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }
} 
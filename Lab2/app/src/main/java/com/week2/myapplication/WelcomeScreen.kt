package com.week2.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Text("Nguyễn Văn A", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("2342312323", fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        // Thay R.drawable.ic_jetpack_compose bằng resource thực tế của bạn
        Box(modifier = Modifier.size(180.dp)) {
            // Image(painter = painterResource(id = R.drawable.ic_jetpack_compose), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text("Jetpack Compose", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navController.navigate("components") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("I'm ready", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
} 
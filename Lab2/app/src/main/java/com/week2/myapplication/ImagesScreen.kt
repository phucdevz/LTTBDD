package com.week2.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.background
// Nếu dùng Coil để load ảnh từ URL:
// import coil.compose.rememberAsyncImagePainter

@Composable
fun ImagesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Images", color = androidx.compose.ui.graphics.Color(0xFF5B9EFF), fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(16.dp))
        // Image từ URL (dùng Coil nếu có)
        /*Image(
            painter = rememberAsyncImagePainter("https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(androidx.compose.ui.graphics.Color.LightGray)
        )
        Text("https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png", fontSize = 12.sp)
        Spacer(Modifier.height(16.dp))
        // Image từ resource (thay R.drawable.in_app_image bằng resource thực tế)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(androidx.compose.ui.graphics.Color.LightGray)
        )
        Text("In app", fontSize = 12.sp)
    }
} 
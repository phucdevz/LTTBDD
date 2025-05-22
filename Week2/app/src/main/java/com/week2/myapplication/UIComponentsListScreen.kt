package com.week2.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun UIComponentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "UI Components List",
            color = Color(0xFF5B9EFF),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        Text("Display", fontWeight = FontWeight.Bold)
        ComponentItem("Text", "Displays text") { navController.navigate("text_detail") }
        ComponentItem("Image", "Displays an image") { navController.navigate("images") }
        Spacer(Modifier.height(8.dp))
        Text("Input", fontWeight = FontWeight.Bold)
        ComponentItem("TextField", "Input field for text") { navController.navigate("textfield") }
        ComponentItem("PasswordField", "Input field for passwords") {}
        Spacer(Modifier.height(8.dp))
        Text("Layout", fontWeight = FontWeight.Bold)
        ComponentItem("Column", "Arranges elements vertically") { navController.navigate("column_layout") }
        ComponentItem("Row", "Arranges elements horizontally") { navController.navigate("row_layout") }
        ComponentItem(
            "Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản",
            backgroundColor = Color(0xFFFFD6D6),
            textColor = Color(0xFFB00020)
        ) {}
    }
}

@Composable
fun ComponentItem(
    title: String,
    desc: String,
    backgroundColor: Color = Color(0xFFD6E6FF),
    textColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != {}, onClick = onClick)
            .padding(12.dp)
    ) {
        Text(title, fontWeight = FontWeight.Bold, color = textColor)
        Text(desc, color = textColor)
    }
} 
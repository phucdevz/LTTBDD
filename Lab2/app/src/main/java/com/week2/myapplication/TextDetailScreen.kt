package com.week2.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.withStyle

@Composable
fun TextDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Text Detail", fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(32.dp))
        Text(
            buildAnnotatedString {
                append("The ")
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick") }
                append(" ")
                withStyle(SpanStyle(color = Color(0xFFB8860B), fontWeight = FontWeight.Bold, fontSize = 28.sp)) { append("Brown") }
                append("\nfox j u m p s ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) { append("over") }
                append("\nthe ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)) { append("lazy") }
                append(" dog.")
            },
            fontSize = 24.sp
        )
        Divider(modifier = Modifier.padding(top = 16.dp))
    }
} 
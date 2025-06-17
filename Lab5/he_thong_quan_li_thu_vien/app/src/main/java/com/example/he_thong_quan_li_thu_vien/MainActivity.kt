package com.example.he_thong_quan_li_thu_vien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.he_thong_quan_li_thu_vien.ui.LibraryManagerContent
import com.example.he_thong_quan_li_thu_vien.ui.theme.He_thong_quan_li_thu_vienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            He_thong_quan_li_thu_vienTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LibraryManagerContent()
                }
            }
        }
    }
}

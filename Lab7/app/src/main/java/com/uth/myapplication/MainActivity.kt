package com.uth.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uth.myapplication.ui.theme.AppTheme
import com.uth.myapplication.ui.theme.MyApplicationTheme
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = applicationContext
            val themeFlow = remember { ThemeDataStore.getThemeFlow(context) }
            val appTheme by themeFlow.collectAsState(initial = AppTheme.LIGHT)
            MyApplicationTheme(appTheme = appTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThemeSettingScreen(
                        currentTheme = appTheme,
                        onThemeSelected = { selectedTheme ->
                            lifecycleScope.launch {
                                ThemeDataStore.setTheme(context, selectedTheme)
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun ThemeSettingScreen(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTheme by remember { mutableStateOf(currentTheme) }
    val backgroundColor = when (selectedTheme) {
        AppTheme.BLUE -> Color(0xFF90CAF9)
        AppTheme.PURPLE -> Color(0xFFBA68C8)
        AppTheme.DARK -> Color(0xFF232323)
        else -> Color.White
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Setting", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Choosing the right theme sets the tone and personality of your app", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ThemeColorOption(AppTheme.BLUE, selectedTheme == AppTheme.BLUE) { selectedTheme = AppTheme.BLUE }
                ThemeColorOption(AppTheme.PURPLE, selectedTheme == AppTheme.PURPLE) { selectedTheme = AppTheme.PURPLE }
                ThemeColorOption(AppTheme.DARK, selectedTheme == AppTheme.DARK) { selectedTheme = AppTheme.DARK }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { onThemeSelected(selectedTheme) }, shape = RoundedCornerShape(24.dp)) {
                Text("Apply")
            }
        }
    }
}

@Composable
fun ThemeColorOption(theme: AppTheme, selected: Boolean, onClick: () -> Unit) {
    val color = when (theme) {
        AppTheme.BLUE -> Color(0xFF90CAF9)
        AppTheme.PURPLE -> Color(0xFFBA68C8)
        AppTheme.DARK -> Color(0xFF232323)
        else -> Color.LightGray
    }
    val border = if (selected) Modifier.border(3.dp, Color.Black, RoundedCornerShape(12.dp)) else Modifier
    Column(
        modifier = Modifier
            .size(64.dp)
            .then(border)
            .background(color, RoundedCornerShape(12.dp))
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {}
}
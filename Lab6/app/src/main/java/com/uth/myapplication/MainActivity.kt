package com.uth.myapplication

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.uth.myapplication.ui.ProfileScreen
import com.uth.myapplication.ui.WelcomeScreen
import com.uth.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import android.util.Log


class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var notificationManager: NotificationManager

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            setupNotifications()
        } else {
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        
        // Initialize Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        
        // Initialize Notification Manager
        notificationManager = NotificationManager(this)
        
        // Request notification permission
        requestNotificationPermission()
        
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SmartTasksApp(
                    onGoogleSignIn = { signIn() },
                    onSignOut = { signOut() }
                )
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!notificationManager.hasNotificationPermission()) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                setupNotifications()
            }
        } else {
            setupNotifications()
        }
    }

    private fun setupNotifications() {
        lifecycleScope.launch {
            val token = notificationManager.getFCMToken()
            fun sendTokenToServer(token: String) {
                Log.d("FCM_TOKEN", "Token: $token") // send token
            }

            token?.let {
                notificationManager.sendTokenToServer(it)
                // Subscribe to general topic
                notificationManager.subscribeToTopic("general")

            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun signOut() {
        auth.signOut()
        googleSignInClient.signOut()
        Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        lifecycleScope.launch {
            try {
                val result = auth.signInWithCredential(credential).await()
                result.user?.let { user ->
                    Toast.makeText(
                        this@MainActivity,
                        "Welcome ${user.displayName}!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Authentication failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun SmartTasksApp(
    onGoogleSignIn: () -> Unit,
    onSignOut: () -> Unit
) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (auth.currentUser != null) "profile" else "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("welcome") {
                WelcomeScreen(
                    onGoogleSignIn = {
                        onGoogleSignIn()
                        // Navigate to profile after successful sign in
                        navController.navigate("profile") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                )
            }
            composable("profile") {
                val user = auth.currentUser
                ProfileScreen(
                    name = user?.displayName ?: "User",
                    email = user?.email ?: "",
                    photoUrl = user?.photoUrl?.toString(),
                    birthDate = null,
                    onBack = {
                        onSignOut()
                        navController.navigate("welcome") {
                            popUpTo("profile") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
package com.uth.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(app: Application) : AndroidViewModel(app) {
    private val context = app.applicationContext
    private val _user = MutableStateFlow<GoogleSignInAccount?>(null)
    val user: StateFlow<GoogleSignInAccount?> = _user

    private val _auth = FirebaseAuth.getInstance()
    private val _gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    val googleSignInClient: GoogleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(context, _gso)

    fun signOut() {
        _auth.signOut()
        googleSignInClient.signOut()
        _user.value = null
    }

    fun onGoogleSignInResult(account: GoogleSignInAccount?, onSuccess: () -> Unit) {
        if (account == null) return
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        viewModelScope.launch {
            _auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user.value = account
                    onSuccess()
                }
            }
        }
    }
} 
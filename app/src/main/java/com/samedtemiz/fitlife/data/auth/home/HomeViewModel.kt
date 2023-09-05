package com.samedtemiz.fitlife.data.auth.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {

    var isUserLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d("ev", "Valid session")
            isUserLoggedIn = true
        } else {
            Log.d("ev", "not logged in")
            isUserLoggedIn = false
        }
    }

    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()
        isLoading = true
        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d("HomeScreen", "Inside sign outsuccess")

                isLoading = false
                isUserLoggedIn = false
            } else {
                Log.d("HomeScreen", "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

}
package com.samedtemiz.fitlife.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel : ViewModel() {

    var isUserLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d("ev", "Valid session")
            isUserLoggedIn = true
            _user.value = FirebaseAuth.getInstance().currentUser
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
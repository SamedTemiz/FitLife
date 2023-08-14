package com.samedtemiz.gastronomyguide.data.auth.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.samedtemiz.gastronomyguide.navigation.AppRouter
import com.samedtemiz.gastronomyguide.navigation.Screen

class HomeViewModel : ViewModel() {

    val isUserLoggedIn : MutableLiveData<Boolean> = MutableLiveData()
    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d("ev", "Valid session")
            isUserLoggedIn.value = true
        }else{
            Log.d("ev", "not logged in")
            isUserLoggedIn.value = false
        }
    }

    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d("HomeScreen", "Inside sign outsuccess")
                AppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d("HomeScreen", "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

}
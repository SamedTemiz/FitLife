package com.samedtemiz.fitlife.ui.main

import android.app.Application
import com.google.firebase.FirebaseApp

class FitLifeApp: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}
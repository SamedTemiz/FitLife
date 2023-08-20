package com.samedtemiz.gastronomyguide.ui.main

import android.app.Application
import com.google.firebase.FirebaseApp

class GuideApp: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}
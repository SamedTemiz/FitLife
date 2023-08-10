package com.samedtemiz.gastronomyguide

import android.app.Application
import com.google.firebase.FirebaseApp

class GastronomyGuideApp: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}
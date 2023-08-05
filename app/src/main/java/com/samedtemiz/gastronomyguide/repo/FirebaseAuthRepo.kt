package com.samedtemiz.gastronomyguide.repo

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthRepo {

    fun login(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ) {

        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(it.exception)
                }
            }
    }

    fun register(
        firebaseAuth: FirebaseAuth,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ) {

        firebaseAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(it.exception)
                }
            }
    }

}
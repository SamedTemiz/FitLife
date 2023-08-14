package com.samedtemiz.gastronomyguide.data.auth.login

import com.google.firebase.auth.FirebaseAuth

data class LoginUIState(
    //Auth
    val auth: FirebaseAuth? = null,

    //Login
    val email: String = "",
    val emailError: String? = null,

    val password: String = "",
    val passwordError: String? = null,

    //UIState
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val loginError: String? = null
)
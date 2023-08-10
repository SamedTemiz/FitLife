package com.samedtemiz.gastronomyguide.data.login

data class LoginUIState(
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
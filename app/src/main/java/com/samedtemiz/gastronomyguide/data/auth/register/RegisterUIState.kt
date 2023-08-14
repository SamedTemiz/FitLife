package com.samedtemiz.gastronomyguide.data.auth.register

data class RegisterUIState(
    //Register
    val firstNameRegister: String = "",
    val firstNameRegisterError: String? = null,

    val lastNameRegister: String = "",
    val lastNameRegisterError: String? = null,

    val emailRegister: String = "",
    val emailRegisterError: String? = null,

    val passwordRegister: String = "",
    val passwordRegisterError: String? = null,

    val confirmPasswordRegister: String = "",
    val confirmPasswordRegisterError: String? = null,

    //UIState
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val registerError: String? = null,
)
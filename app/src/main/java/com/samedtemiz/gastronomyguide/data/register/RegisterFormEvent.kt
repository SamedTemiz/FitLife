package com.samedtemiz.gastronomyguide.data.register

import com.samedtemiz.gastronomyguide.data.login.LoginFormEvent

sealed class RegisterFormEvent{
    data class FirstNameChanged(val firstName: String) : RegisterFormEvent()
    data class LastNameChanged(val lastName: String) : RegisterFormEvent()
    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterFormEvent()

    object Submit: RegisterFormEvent()
}

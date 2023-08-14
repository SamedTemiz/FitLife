package com.samedtemiz.gastronomyguide.data.auth.login

import com.samedtemiz.gastronomyguide.data.auth.register.RegisterFormEvent

sealed class LoginFormEvent{
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()

    object Submit: LoginFormEvent()
}

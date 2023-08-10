package com.samedtemiz.gastronomyguide.data.login

import com.samedtemiz.gastronomyguide.data.register.RegisterFormEvent

sealed class LoginFormEvent{
    data class EmailChanged(val email: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()

    object Submit: LoginFormEvent()
}

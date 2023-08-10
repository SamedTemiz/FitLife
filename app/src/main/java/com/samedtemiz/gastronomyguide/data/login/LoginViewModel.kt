package com.samedtemiz.gastronomyguide.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.gastronomyguide.data.login.LoginFormEvent
import com.samedtemiz.gastronomyguide.data.login.LoginUIState
import com.samedtemiz.gastronomyguide.data.register.RegisterValidationEvent
import com.samedtemiz.gastronomyguide.data.use_case.ValidateEmail
import com.samedtemiz.gastronomyguide.data.use_case.ValidatePassword
import com.samedtemiz.gastronomyguide.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: AuthRepository = AuthRepository(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
) : ViewModel() {

    var state by mutableStateOf(LoginUIState())

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    private val validationEventChannel = Channel<LoginValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
                login()
            }
        }

    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
        }

        viewModelScope.launch {
            validationEventChannel.send(LoginValidationEvent.Success)
        }
    }

    private fun login() = viewModelScope.launch {

        try {
            state = state.copy(isLoading = true)
            state = state.copy(loginError = null)

            validationEvents.collect { event ->
                when (event) {
                    is LoginValidationEvent.Success -> {

                        state = state.copy(loginError = null)

                        repository.login(
                            state.email,
                            state.password
                        ) { isSuccessful ->
                            state = if (isSuccessful) {

                                state.copy(isSuccessLogin = true)

                            } else {

                                state.copy(isSuccessLogin = false)
                            }

                        }
                    }

                    else -> {}
                }
            }

        } catch (e: Exception) {
            state = state.copy(loginError = "Email or password is incorrect")

            e.printStackTrace()
        } finally {
            state = state.copy(isLoading = false)
        }

    }

    fun logoutUser() {
        try {
            state = state.copy(isLoading = true)
            repository.logout()
        } catch (e: Exception) {
            e.printStackTrace()
        }finally {
            state = state.copy(isLoading = false)
        }

    }
}

sealed class LoginValidationEvent() {
    object Success : LoginValidationEvent()
}
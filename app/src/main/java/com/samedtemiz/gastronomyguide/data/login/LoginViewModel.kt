package com.samedtemiz.gastronomyguide.data.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.gastronomyguide.data.FieldsValidator
import com.samedtemiz.gastronomyguide.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: AuthRepository = AuthRepository(),
) : ViewModel() {

    var state by mutableStateOf(LoginUIState())

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    private val validationEventChannel = Channel<LoginValidationEvent>()
    private val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                validationDataWithLoginRules()
                login()
            }
        }

    }

    private fun validationDataWithLoginRules() {
        val emailResult = FieldsValidator.validateEmail(state.email)
        val passwordResult = FieldsValidator.validatePassword(state.password)

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

sealed class LoginValidationEvent {
    object Success : LoginValidationEvent()
}
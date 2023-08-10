package com.samedtemiz.gastronomyguide.data.register

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samedtemiz.gastronomyguide.data.LoginValidationEvent
import com.samedtemiz.gastronomyguide.data.login.LoginFormEvent
import com.samedtemiz.gastronomyguide.data.use_case.ValidateConfirmPassword
import com.samedtemiz.gastronomyguide.data.use_case.ValidateEmail
import com.samedtemiz.gastronomyguide.data.use_case.ValidateFirstName
import com.samedtemiz.gastronomyguide.data.use_case.ValidateLastName
import com.samedtemiz.gastronomyguide.data.use_case.ValidatePassword
import com.samedtemiz.gastronomyguide.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository = AuthRepository(),
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateConfirmPassword = ValidateConfirmPassword()
) : ViewModel() {

    var state by mutableStateOf(RegisterUIState())

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()


    private val validationEventChannel = Channel<RegisterValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent) {

        when (event) {
            is RegisterFormEvent.FirstNameChanged -> {
                state = state.copy(firstNameRegister = event.firstName)
            }

            is RegisterFormEvent.LastNameChanged -> {
                state = state.copy(lastNameRegister = event.lastName)
            }

            is RegisterFormEvent.EmailChanged -> {
                state = state.copy(emailRegister = event.email)
            }

            is RegisterFormEvent.PasswordChanged -> {
                state = state.copy(passwordRegister = event.password)
            }

            is RegisterFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPasswordRegister = event.confirmPassword)
            }

            is RegisterFormEvent.Submit -> {
                submitData()
                register()
            }

        }

    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(state.firstNameRegister)
        val lastNameResult = validateLastName.execute(state.lastNameRegister)
        val emailResult = validateEmail.execute(state.emailRegister)
        val passwordResult = validatePassword.execute(state.passwordRegister)
        val confirmPasswordResult =
            validateConfirmPassword.execute(state.passwordRegister, state.confirmPasswordRegister)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                firstNameRegisterError = firstNameResult.errorMessage,
                lastNameRegisterError = lastNameResult.errorMessage,
                emailRegisterError = emailResult.errorMessage,
                passwordRegisterError = passwordResult.errorMessage,
                confirmPasswordRegisterError = confirmPasswordResult.errorMessage
            )
        }

        viewModelScope.launch {
            validationEventChannel.send(RegisterValidationEvent.Success)
        }
    }

    private fun register() = viewModelScope.launch {

        try {
            state = state.copy(isLoading = true)
            validationEvents.collect{event ->
                when (event) {
                    is RegisterValidationEvent.Success -> {

                        state = state.copy(registerError = null)

                        repository.createUser(
                            state.emailRegister,
                            state.passwordRegister
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
            state = state.copy(registerError = e.localizedMessage)

            e.printStackTrace()
        } finally {
            state = state.copy(isLoading = false)
        }

    }
}

sealed class RegisterValidationEvent() {
    object Success : RegisterValidationEvent()
}
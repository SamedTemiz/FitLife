package com.samedtemiz.gastronomyguide.data.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.samedtemiz.gastronomyguide.data.auth.FieldsValidator
import com.samedtemiz.gastronomyguide.navigation.AppRouter
import com.samedtemiz.gastronomyguide.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegisterUIState())

    private val validationEventChannel = Channel<RegisterValidationEvent>()
    private val validationEvents = validationEventChannel.receiveAsFlow()

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
        val firstNameResult = FieldsValidator.validateFirstName(state.firstNameRegister)
        val lastNameResult = FieldsValidator.validateLastName(state.lastNameRegister)
        val emailResult = FieldsValidator.validateEmail(state.emailRegister)
        val passwordResult = FieldsValidator.validatePassword(state.passwordRegister)
        val confirmPasswordResult =
            FieldsValidator.validateConfirmPassword(
                state.passwordRegister,
                state.confirmPasswordRegister
            )

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
        } else {
            viewModelScope.launch {
                validationEventChannel.send(RegisterValidationEvent.Success)
            }
        }
    }

    private fun register() = viewModelScope.launch {

        try {

            validationEvents.collect { event ->
                when (event) {
                    is RegisterValidationEvent.Success -> {
                        state = state.copy(isLoading = true, registerError = null)

                        firebaseRegister(
                            state.emailRegister,
                            state.passwordRegister
                        ) { isSuccessful ->

                            if (isSuccessful) {
                                AppRouter.navigateTo(Screen.HomeScreen)
                                state = state.copy(isSuccessLogin = true)
                            } else {
                                state = state.copy(
                                    isSuccessLogin = false,
                                    isLoading = false
                                )
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

    private suspend fun firebaseRegister(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ) = withContext(Dispatchers.IO) {

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }
}


sealed class RegisterValidationEvent() {
    object Success : RegisterValidationEvent()
}
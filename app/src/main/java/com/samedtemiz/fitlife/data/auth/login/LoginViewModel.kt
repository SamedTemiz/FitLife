package com.samedtemiz.fitlife.data.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.samedtemiz.fitlife.data.auth.AuthValidator
import com.samedtemiz.fitlife.navigation.AppRouter
import com.samedtemiz.fitlife.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginUIState())

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
                // Bilgiler kurallara uygunsa login() metodu çalışıyor
                validationDataWithLoginRules()
                login()
            }

        }

    }

    private fun validationDataWithLoginRules() {
        val emailResult = AuthValidator.validateEmail(state.email)
        val passwordResult = AuthValidator.validatePassword(state.password)

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

            validationEvents.collect { event ->
                when (event) {

                    is LoginValidationEvent.Success -> {
                        state = state.copy(isLoading = true, loginError = null)
                        firebaseLogin(
                            state.email,
                            state.password
                        ) { isSuccessful ->

                            if (isSuccessful) {
                                state = state.copy(isSuccessLogin = true, isLoading = false)

                                AppRouter.navigateTo(Screen.MainScreen)
                            } else {
                                state = state.copy(
                                    isSuccessLogin = false,
                                    isLoading = false,
                                    loginError = "Email or password is incorrect"
                                )
                            }

                        }
                    }

                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            state = state.copy(isLoading = false)
        }
    }

    private suspend fun firebaseLogin(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ) = withContext(Dispatchers.IO) {

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

}

sealed class LoginValidationEvent {
    object Success : LoginValidationEvent()
}
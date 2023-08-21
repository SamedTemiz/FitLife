package com.samedtemiz.fitlife.ui.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.samedtemiz.fitlife.R
import com.samedtemiz.fitlife.app.AppSettings
import com.samedtemiz.fitlife.components.ButtonComponent
import com.samedtemiz.fitlife.components.ClickableTextComponent
import com.samedtemiz.fitlife.components.NormalTextBoxComponent
import com.samedtemiz.fitlife.components.PasswordTextBoxComponent
import com.samedtemiz.fitlife.data.auth.register.RegisterFormEvent
import com.samedtemiz.fitlife.data.auth.register.RegisterUIState
import com.samedtemiz.fitlife.data.auth.register.RegisterViewModel
import com.samedtemiz.fitlife.navigation.AppRouter
import com.samedtemiz.fitlife.navigation.Screen

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen()
    }
}

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel()) {

    val state = registerViewModel.state
    val isDarkMode = AppSettings.isDarkMode(LocalContext.current)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = if(isDarkMode) R.drawable.food_bg_dark else R.drawable.food_bg_light
                ),
                contentDescription = "Login",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(6.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
                    .alpha(0.6f)
                    .clip(
                        CutCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.background)
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                RegisterHeader()

                RegisterFields(registerViewModel, state)

                RegisterFooter(
                    onRegisterClick = {
                        registerViewModel.onEvent(RegisterFormEvent.Submit)
                    },
                    onLoginClick = {
                        AppRouter.navigateTo(Screen.LoginScreen)
                    },
                    enabledStatus = true
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun RegisterHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.esprit_bold)
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Create an account",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(
                Font(R.font.esprit_bold)
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun RegisterFields(registerViewModel: RegisterViewModel, state: RegisterUIState) {
    Column {

        //FIRST NAME
        NormalTextBoxComponent(
            label = "First Name",
            placeholder = "Enter your first name",
            supportingText = state.firstNameRegisterError ?: "unknown error",
            onTextSelected = {
                registerViewModel.onEvent(RegisterFormEvent.FirstNameChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "First Name")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            errorStatus = state.firstNameRegisterError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        //LAST NAME
        NormalTextBoxComponent(
            label = "Last Name",
            placeholder = "Enter your last name",
            supportingText = state.lastNameRegisterError ?: "unknown error",
            onTextSelected = {
                registerViewModel.onEvent(RegisterFormEvent.LastNameChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Last Name")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            errorStatus = state.lastNameRegisterError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        //EMAIL
        NormalTextBoxComponent(
            label = "Email",
            placeholder = "Enter your email address",
            supportingText = state.emailRegisterError ?: "unknown error",
            onTextSelected = {
                registerViewModel.onEvent(RegisterFormEvent.EmailChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            errorStatus = state.emailRegisterError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        val showPassword = remember { mutableStateOf(false) }
        //PASSWORD
        PasswordTextBoxComponent(
            label = "Password",
            placeholder = "Enter your password",
            supportingText = state.passwordRegisterError ?: "unknown error",
            onTextSelected = {
                registerViewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
            },
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        Icons.Filled.Visibility,
                        Color(0xFF40484d)
                    )
                } else {
                    Pair(Icons.Filled.VisibilityOff, Color(0xFF40484d))
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            errorStatus = state.passwordRegisterError != null
        )

        val showConfirmPassword = remember { mutableStateOf(false) }
        //CONFIRM PASSWORD
        PasswordTextBoxComponent(
            label = "Confirm Password",
            placeholder = "Password again",
            supportingText = state.confirmPasswordRegisterError ?: "unknown error",
            onTextSelected = {
                registerViewModel.onEvent(RegisterFormEvent.ConfirmPasswordChanged(it))
            },
            visualTransformation = if (showConfirmPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showConfirmPassword.value) {
                    Pair(
                        Icons.Filled.Visibility,
                        Color(0xFF40484d)
                    )
                } else {
                    Pair(Icons.Filled.VisibilityOff, Color(0xFF40484d))
                }

                IconButton(onClick = { showConfirmPassword.value = !showConfirmPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Confirm Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            errorStatus = state.confirmPasswordRegisterError != null
        )
    }
}

@Composable
fun RegisterFooter(
    onRegisterClick: () -> Unit,
    onLoginClick: (String) -> Unit,
    enabledStatus: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ButtonComponent(
            value = "Register",
            onButtonClicked = onRegisterClick,
            isEnabled = enabledStatus
        )

        Spacer(modifier = Modifier.height(5.dp))

        ClickableTextComponent(
            tryingToLogin = false,
            onTextSelected = onLoginClick
        )
    }
}


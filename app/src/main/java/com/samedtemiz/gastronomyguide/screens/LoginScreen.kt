package com.samedtemiz.gastronomyguide.screens

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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samedtemiz.gastronomyguide.R
import com.samedtemiz.gastronomyguide.components.ButtonComponent
import com.samedtemiz.gastronomyguide.components.ClickableTextComponent
import com.samedtemiz.gastronomyguide.components.NormalTextBoxComponent
import com.samedtemiz.gastronomyguide.components.PasswordTextBoxComponent
import com.samedtemiz.gastronomyguide.data.LoginViewModel
import com.samedtemiz.gastronomyguide.data.login.LoginFormEvent
import com.samedtemiz.gastronomyguide.data.login.LoginUIState

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavToHomePage = { /*TODO*/ }) {

    }
}

@Composable
fun LoginScreen(
    onNavToHomePage: () -> Unit,
    onNavToRegisterPage: () -> Unit
) {
    val loginViewModel = viewModel<LoginViewModel>()
    val state = loginViewModel.state
//    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.deneme_bg),
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
                    .alpha(0.5f)
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

                LoginHeader()

                LoginFields(loginViewModel, state)

                LoginFooter(
                    onLoginClick = {
                        loginViewModel.onEvent(LoginFormEvent.Submit)
                    },
                    onRegisterClick = {
                        onNavToRegisterPage.invoke()
                    },
                    enabledStatus = true
                )

            }


        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(key1 = loginViewModel.hasUser) {
        if (loginViewModel.hasUser) {
            onNavToHomePage.invoke()
        }
    }
}


@Composable
fun LoginHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome Back", fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Sign in to continue",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun LoginFields(loginViewModel: LoginViewModel, state: LoginUIState) {
    Column {
        if(state.loginError != null){
            Text(text = state.loginError, color = Color.Red)
        }
        //EMAIL
        NormalTextBoxComponent(
            label = "Email",
            placeholder = "Enter your email address",
            supportingText = state.emailError ?: "unknown error",
            onTextSelected = {
                loginViewModel.onEvent(LoginFormEvent.EmailChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            errorStatus = state.emailError != null
        )

        Spacer(modifier = Modifier.height(8.dp))

        val showPassword = remember { mutableStateOf(false) }
        //PASSWORD
        PasswordTextBoxComponent(
            label = "Password",
            placeholder = "Enter your password",
            supportingText = state.passwordError ?: "unknown error",
            onTextSelected = {
                loginViewModel.onEvent(LoginFormEvent.PasswordChanged(it))
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
                imeAction = ImeAction.Go
            ),
            errorStatus = state.passwordError != null
        )

        TextButton(
            onClick = {}, modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(text = "Forgot your password?")
        }
    }
}

@Composable
fun LoginFooter(
    onLoginClick: () -> Unit,
    onRegisterClick: (String) -> Unit,
    enabledStatus: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ButtonComponent(
            value = "Login",
            onButtonClicked = onLoginClick,
            isEnabled = enabledStatus
        )

        Spacer(modifier = Modifier.height(5.dp))

        ClickableTextComponent(
            tryingToLogin = true,
            onTextSelected = onRegisterClick
        )
    }
}
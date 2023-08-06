package com.samedtemiz.gastronomyguide.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samedtemiz.gastronomyguide.R
import com.samedtemiz.gastronomyguide.components.ButtonComponent
import com.samedtemiz.gastronomyguide.components.ClickableLoginTextComponent
import com.samedtemiz.gastronomyguide.components.DividerTextComponent
import com.samedtemiz.gastronomyguide.components.HeadingTextComponent
import com.samedtemiz.gastronomyguide.components.MyTextFieldComponent
import com.samedtemiz.gastronomyguide.components.NormalTextComponent
import com.samedtemiz.gastronomyguide.components.PasswordTextFieldComponent

@Composable
fun SingUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        //Header
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            NormalTextComponent(value = "Hey there, ")
            HeadingTextComponent(value = "Create an Account")

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                label = "First Name",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user_64),
                        contentDescription = null
                    )
                }
            )

            MyTextFieldComponent(
                label = "Last Name",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user_64),
                        contentDescription = null
                    )
                }
            )

            MyTextFieldComponent(
                label = "E-mail",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email_64),
                        contentDescription = null
                    )
                }
            )

            PasswordTextFieldComponent(
                label = "Password",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock_64),
                        contentDescription = null
                    )
                }
            )

            Spacer(modifier = Modifier.height(80.dp))

            ButtonComponent(value = "Register")

            DividerTextComponent()

            ClickableLoginTextComponent(onTextSelected = {

            })
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SingUpScreen()
}
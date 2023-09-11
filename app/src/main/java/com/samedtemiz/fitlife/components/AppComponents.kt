package com.samedtemiz.fitlife.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose.BurntSienna_300
import com.example.compose.BurntSienna_400
import com.example.compose.BurntSienna_500
import com.example.compose.BurntSienna_600
import com.example.compose.Comet_300
import com.example.compose.Comet_500
import com.example.compose.Licorice_400
import com.example.compose.Licorice_600
import com.example.compose.Matisse_500
import com.example.compose.Matisse_700
import com.samedtemiz.fitlife.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTextBoxComponent(
    label: String,
    placeholder: String,
    supportingText: String,
    onTextSelected: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        supportingText = {
            if (errorStatus) {
                Text(text = supportingText)
            }
        },
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = 1,
        singleLine = true,
        isError = errorStatus,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = BurntSienna_500,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Comet_300,
            errorBorderColor = BurntSienna_400,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Comet_300,
            errorLeadingIconColor = BurntSienna_400,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Comet_300,
            errorLabelColor = BurntSienna_400,
            placeholderColor = Comet_300,
            errorSupportingTextColor = BurntSienna_400,
            selectionColors = TextSelectionColors(handleColor = Color.White, backgroundColor = Comet_500)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextBoxComponent(
    label: String,
    placeholder: String,
    supportingText: String,
    onTextSelected: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        supportingText = {
            if (errorStatus) {
                Text(text = supportingText)
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = 1,
        singleLine = true,
        isError = errorStatus,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = BurntSienna_500,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Comet_300,
            errorBorderColor = BurntSienna_400,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Comet_300,
            errorLeadingIconColor = BurntSienna_400,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Comet_300,
            errorLabelColor = BurntSienna_400,
            placeholderColor = Comet_300,
            errorSupportingTextColor = BurntSienna_400,
            selectionColors = TextSelectionColors(handleColor = Color.White, backgroundColor = Comet_500)
        )
    )
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        onClick = {
            onButtonClicked.invoke()
        },
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = BurntSienna_500,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.avenir_next)
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )

        }

    }
}


@Composable
fun ClickableTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Don't have an account yet? " else "Already have an account? "
    val loginText = if (tryingToLogin) "Register" else "Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        ) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            color = Comet_300,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(R.font.avenir_next, weight = FontWeight.Normal)
            ),
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarTextComponent(
    label: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = BurntSienna_500,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Comet_300,
            errorBorderColor = BurntSienna_400,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Comet_300,
            errorLeadingIconColor = BurntSienna_400,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Comet_300,
            errorLabelColor = BurntSienna_400,
            placeholderColor = Comet_300,
            errorSupportingTextColor = BurntSienna_400,
            selectionColors = TextSelectionColors(handleColor = Color.White, backgroundColor = Comet_500)
        )
    )
}
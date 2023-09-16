package com.samedtemiz.fitlife.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.BurntSienna_500
import com.example.compose.BurntSienna_900
import com.example.compose.Comet_300
import com.example.compose.Licorice_500
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
    content: @Composable () -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    HandleRequest(
        permissionState = permissionState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = { permissionState.launchPermissionRequest() }
            )
        },
        content = { content() }
    )
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }

        is PermissionStatus.Denied -> {
            deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
        }
    }
}

@Composable
fun Content(text: String, showButton: Boolean = true, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Licorice_500),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = Comet_300,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (showButton) {
                Button(
                    onClick = onClick, colors = ButtonDefaults.buttonColors(
                        backgroundColor = BurntSienna_500,
                        contentColor = BurntSienna_900
                    )
                ) {
                    Text(text = "Request")
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    deniedMessage: String,
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    if (shouldShowRationale) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(
                    onClick = onRequestPermission, colors = ButtonDefaults.buttonColors(
                        backgroundColor = BurntSienna_500,
                        contentColor = BurntSienna_900
                    )
                ) {
                    Text("Give Permission")
                }
            }
        )
    } else {
        Content(text = deniedMessage, onClick = onRequestPermission)
    }

}
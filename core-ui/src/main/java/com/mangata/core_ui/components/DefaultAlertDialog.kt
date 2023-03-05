package com.mangata.core_ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultAlertDialog(
    title: String,
    description: String,
    confirmButtonText: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(onClick = {
                onConfirmButtonClick()
            }) {
                Text(text = confirmButtonText)
            }
        },
    )
}
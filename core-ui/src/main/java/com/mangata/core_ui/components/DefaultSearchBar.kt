package com.mangata.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.theme.componentBackground
import com.mangata.core_ui.theme.textPrimary

@Composable
fun DefaultSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeholderText: String,
    onTextChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Surface(modifier = modifier
        .height(56.dp),
        color = MaterialTheme.colors.componentBackground,
        shape = RoundedCornerShape(percent = 10)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.textPrimary,
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary.copy(ContentAlpha.medium),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    color = MaterialTheme.colors.textPrimary.copy(alpha = ContentAlpha.medium),
                    text = placeholderText)
            },
            leadingIcon = {
                  Icon(
                      imageVector = Icons.Outlined.Search,
                      contentDescription = "Search Icon",
                      tint = MaterialTheme.colors.textPrimary
                  )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable { onTextChange("") },
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "Delete Search",
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            )
        )
    }
}
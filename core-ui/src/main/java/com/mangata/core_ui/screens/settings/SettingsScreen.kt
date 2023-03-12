package com.mangata.core_ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.R
import com.mangata.core_ui.theme.textPrimary

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBuyMeACoffeeClick: () -> Unit,
    onBackCLick: () -> Unit,
) {
    Scaffold(
        topBar = { SettingsTopAppBar(onBackCLick) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            SupportContent(
                modifier = Modifier.align(Alignment.Center),
                onBuyMeACoffeeClick = onBuyMeACoffeeClick
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter),
                text = " Version: ${viewModel.getAppVersion()}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.textPrimary,
            )
        }
    }
}

@Composable
private fun SupportContent(
    modifier: Modifier = Modifier,
    onBuyMeACoffeeClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Want to support my work?",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.textPrimary,
            )
            Image(
                modifier = Modifier.fillMaxWidth(0.6f).clickable { onBuyMeACoffeeClick() },
                painter = painterResource(id = R.drawable.buy_me_a_coffee),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun SettingsTopAppBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.textPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
    )
}
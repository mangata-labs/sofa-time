package com.mangata.core_ui.screens.settings

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangata.core_ui.R
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.util.linkToWebPage

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackCLick: () -> Unit,
) {

    val context = LocalContext.current

    Scaffold(
        topBar = { SettingsTopAppBar(onBackCLick) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            SupportMeContent(
                modifier = Modifier.align(Alignment.Center),
                buyMeACoffeeUrlPath = viewModel.getBuyMeACoffeeUrlPath(),
                context = context
            )
            FooterContent(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter),
                appVersion = viewModel.getAppVersion(),
                tmdbUrl = viewModel.getTmdbUrl(),
                context = context
            )
        }
    }
}

@Composable
private fun SupportMeContent(
    modifier: Modifier = Modifier,
    buyMeACoffeeUrlPath: String,
    context: Context
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Want to support app development?",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.textPrimary,
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .clickable { linkToWebPage(context, buyMeACoffeeUrlPath) },
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

@Composable
private fun FooterContent(
    modifier: Modifier = Modifier,
    appVersion: String,
    context: Context,
    tmdbUrl: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.width(220.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "All Tv Show data is supplied by",
                style = MaterialTheme.typography.body1,
                fontSize = 15.sp,
                color = MaterialTheme.colors.textPrimary,
            )
            Image(
                modifier = Modifier.clickable { linkToWebPage(context, tmdbUrl) },
                painter = painterResource(id = R.drawable.tmdb_logo),
                contentDescription = null
            )
        }
        Text(
            text = "Version: $appVersion",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.textPrimary,
        )
    }
}
package com.onetoucheasy.restauranteofertas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.onetoucheasy.restauranteofertas.R

@Composable
fun TopBar(onBackClick: (Unit) -> Unit = {} ){
    TopBarContent(onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarContent(onBackClick: (Unit) -> Unit = {}) {

    TopAppBar(
        title = {
            Text(stringResource(id = R.string.back))
        },
        navigationIcon = { BackButton(onBackClick) },
        modifier = Modifier.testTag("extended_top_bar")
//            .padding(bottom = 6.dp)
    )
}

@Composable
fun BackButton(onBackClick: (Unit) -> (Unit) = {}) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        tint = Color.Black,
        contentDescription = "Back button to offer list",
        modifier = Modifier.clickable {
            onBackClick(Unit)
        }
    )
}

@Preview
@Composable
fun TopBar_Preview() {
    TopBarContent()
}

package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.ui.theme.White
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel, userType: String) {
    MainScreenContent()
}

@Composable
fun MainScreenContent() {
    Text("This is the main screen")
}

@Preview
@Composable
fun MainScreen_Preview() {
    MainScreenContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(text: String,
                 screenWidth: Dp,
                 onValueChange: (String) -> (Unit))
{
    TextField(value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(screenWidth * 4.0f / 5)
            .background(White)
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                BorderStroke(
                    0.5.dp,
                    Color.Black
                ), RoundedCornerShape(8.dp)
            ),
        trailingIcon = {
            Icon(Icons.Default.Search,
                contentDescription  = stringResource(id = R.string.main_view_search_bar_hint),
                tint = Color.Black)
        },
        placeholder = {
                FormLabel(hint = stringResource(R.string.main_view_search_bar_hint), TextDecoration.None)
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
    )
}

@Preview
@Composable
fun SearchBar_Preview() {
    SearchBar("", screenWidth = 500.dp) { _ -> }
}
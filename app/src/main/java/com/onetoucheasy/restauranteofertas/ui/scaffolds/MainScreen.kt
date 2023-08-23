package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.ui.theme.Black
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


@Composable
fun OfferItem(screenWidth: Dp, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .width(screenWidth * 4f / 5)
            .height(200.dp)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            ),

        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)//, t = MaterialTheme.colorScheme.surfaceVariant,
        )

    ) {
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
            AsyncImage(
                model = "https://www.equipamientointegraldeoficinas.com/wp-content/uploads/Post-11-6.png",//serie.thumbnail.path + "."+ serie.thumbnail.extension,
                contentDescription = "",//${NombreRestaruante}",
                placeholder = painterResource(R.mipmap.image_resto_example),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Row() {
                Text(
                    text = "Restaurante Pepito",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top= 30.dp))
            }
        }

        Text(
            text = "20% de descuento en carta",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 20.dp, top= 20.dp))
        Text(
            text = "De 15:30 a 18:30",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp, top = 10.dp))
    }
}

@Preview
@Composable
fun OfferItem_Preview() {
    OfferItem(500.dp)
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(text: String,
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
fun CustomSearchBar_Preview() {
    CustomSearchBar("", screenWidth = 500.dp) { _ -> }
}



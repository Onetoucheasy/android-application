package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.localRestaurantMock1
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun RestaurantScreen (viewModel: MainScreenViewModel, id: String) {
    val restaurantState by viewModel.stateRestaurants.collectAsState()
    val restaurant = restaurantState.find { it.id == id }
    val telephoneMock: String = "Tel: +34.123.456.789"
    val websiteMock: String = "www.ejemplo.es"

    LaunchedEffect(Unit){
        Log.d("Tag","🍕 RestaurantScreen > Restaurant id: $id")
    }
    if (restaurant != null) {
        RestaurantScreenContent(
            restaurant = restaurant,
            telephone = telephoneMock,
            website = websiteMock
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreenContent (
    restaurant: LocalRestaurant,
    telephone: String,
    website: String
) {
    Scaffold(
    ) { it ->
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            item {
                Text(
                    text = "Restaurante", // tested, pass
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                DetailRestaurantItem(
                    restaurant = restaurant,
                    telephone = telephone,
                    website = website
                )
            }
        }
    }
}

@Composable
fun DetailRestaurantItem(
    restaurant: LocalRestaurant,
    modifier: Modifier = Modifier,
    telephone: String,
    website: String
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
//            .height(750.dp),
//        .fillMaxHeight(), // image doesn't appear using this
            .requiredHeightIn(min(200.dp, 200.dp), max = 650.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)
        )
    ) {
        Text(text = restaurant.name.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier
            .padding(8.dp)
//            .weight(1f)
        )
        Text(text = "Horario: 12:00 a 23:00", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = restaurant.picture,
            contentDescription = restaurant.name,
            placeholder = painterResource(R.mipmap.image_resto_example),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = "Ésta restaurante, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        ClickableContactLink(info = telephone)
        ClickableContactLink(info = website)
    }
}
@Composable
fun ClickableContactLink(info: String) {
    val annotatedText = remember {
        AnnotatedString.Builder(info)
            .apply {
                // Add a URL annotation that covers the whole text
                addStringAnnotation(
                    tag = "URL",
                    annotation = "tel:+34123456789",
                    start = 0,
                    end = length
                )
                // Add a span style to make it look like a link
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue), 0, length)
            }
            .toAnnotatedString()
    }

    val context = LocalContext.current

    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val urlString = annotatedText
                    .getStringAnnotations("URL", 0, annotatedText.length)
                    .firstOrNull()?.item
                if (urlString != null) {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(urlString))
                    context.startActivity(intent)
                }
            }
    )
}
@Preview(showBackground = true)
@Composable
fun RestaurantScreen_Preview() {
    RestaurantScreenContent(localRestaurantMock1, telephone = "Tel: +34.123.456.789", website = "www.example.com")
}
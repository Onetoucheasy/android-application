package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.localRestaurantMock1
import com.onetoucheasy.restauranteofertas.repository.offerMock11
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.ui.QRCodeGenerator
import com.onetoucheasy.restauranteofertas.ui.components.TopBar
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen (
    viewModel: MainScreenViewModel,
    offerId: String,
    onBackClick: (Unit) -> Unit = { _ -> }
) {
    val restaurantState by viewModel.stateRestaurants.collectAsState()
    val offer = restaurantState.find { restaurant ->
        restaurant.offers.any { offer -> offer.id == offerId }
    }?.offers?.find { it.id == offerId }
    val restaurant = restaurantState.find { restaurant ->
        restaurant.offers.any { offer -> offer.id == offerId }
    }

    LaunchedEffect(Unit){
        Log.d("Tag", "🎉 DetailScreen > offer id: $offerId")
        val test = viewModel.getOfferById(offerId)
    }
    if (offer != null) {
        if (restaurant != null) {
            DetailScreenContent(
                offer = offer,
                restaurant = restaurant,
                onBackClick = onBackClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    offer: Offer,
    restaurant: LocalRestaurant,
    onBackClick: (Unit) -> Unit
){
    Scaffold(
    ) {
        LazyColumn(
            Modifier
                .padding(9.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp),
            contentPadding = it
        ) {
            item {
                TopBar(onBackClick = onBackClick)
            }
            item {
                DetailOfferItem(
                    offer = offer,
                    restaurant = restaurant,
                    modifier = Modifier.padding(9.dp)
                )
            }
            item {
                ReviewSection(modifier = Modifier.padding(9.dp))
            }
            item {
                RestaurantSection(
                    restaurant = restaurant,
                    modifier = Modifier.padding(9.dp)
                )
            }
        }
    }
}
@Composable
fun DetailOfferItem(
    offer: Offer,
    restaurant: LocalRestaurant,
    modifier: Modifier = Modifier
) {
    Card(
        Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .border(
                width = 0.2.dp,
                color = Color(0xFF383737),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .fillMaxWidth()
            .height(650.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00000000))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.dp)
        ) {
            Text(
                text = offer.offerName.toString(),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 9.dp)
                    .weight(0.1f))
            FavoriteHeart(modifier = Modifier
                .padding(top = 9.dp)
            )
        }
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "${offer.startTime.substringAfter("T").substringBefore(":00Z")} hs a ${offer.endTime.substringAfter("T").substringBefore(":00Z")} hs",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
        AsyncImage(
            model = offer.image,
            contentDescription = offer.description,
            placeholder = painterResource(R.mipmap.image_resto_example),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = offer.description.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
        QRCodeGenerator(
            data = offer.id,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(bottom = 9.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun FavoriteHeart(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Default.FavoriteBorder,
        contentDescription = "Favorite",
        modifier = Modifier
            .size(32.dp)
            .clickable {
                Log.d("Tag", "Favorite clicked!")
            }
    )
}
@Composable
fun ReviewStar(ifYes: Boolean) {
    if (ifYes) {
        androidx.compose.material3.Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.Star,
            contentDescription = "Star",
            modifier = androidx.compose.ui.Modifier
                .size(28.dp)
        )
    } else {
        androidx.compose.material3.Icon(
            imageVector = androidx.compose.material.icons.Icons.Outlined.Star,
            contentDescription = "Star",
            modifier = androidx.compose.ui.Modifier
                .size(28.dp)
        )
    }

}
@Composable
fun ReviewSection(modifier: Modifier = Modifier) {
    Card(
        Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .border(
                width = 0.2.dp,
                color = Color(0xFF383737),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .fillMaxWidth()
            .heightIn(100.dp, 500.dp)
            .wrapContentHeight(Alignment.Top)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00000000))
    ) {
        Text(
            text = "Reseñas",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp))
        Row {
            Text(
                text = "¡Muy buena!",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(8.dp))
            ReviewStar(true)
            ReviewStar(true)
            ReviewStar(true)
            ReviewStar(true)
            ReviewStar(false)
        }
        Text(
            text = "Cenamos en \"Tapas Bar\" anoche y quedamos completamente encantados. Desde el momento en que entramos, el ambiente fue acogedor, con suaves melodías de jazz llenando el espacio. El menú fue una deliciosa fusión de clásico y contemporáneo, y cada plato demostraba la destreza del chef. El servicio fue impecable, haciendo nuestra noche verdaderamente inolvidable. ¡Altamente recomendado!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
        ButtonPrincipal(
            width = 200.dp,
            stringResource = R.string.leave_review,
            modifier = modifier
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally)
                .padding(6.dp)
                .clickable { Log.d("Tag", "Review button pressed") }
        )
    }
}

@Composable
fun RestaurantSection (
    restaurant: LocalRestaurant,
    modifier: Modifier = Modifier
) {
    Card(
        Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .border(
                width = 0.2.dp,
                color = Color(0xFF383737),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .fillMaxWidth()
//            .fillMaxHeight()
            .heightIn(100.dp, 400.dp)
            .wrapContentHeight(Alignment.Top)
//            .verticalScroll()
//            .height(450.dp)
            .background(
                color = Color.White,//Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x00000000)
        )
    ) {
        Text(
            text = restaurant.name.toString(),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(8.dp)
//            .weight(1f)
        )
        Text(
            text = "Horario: 12:00 a 23:00",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
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
        ButtonPrincipal(
            width = 200.dp,
            stringResource = R.string.open_table,
            modifier = modifier
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally)
                .padding(6.dp)
                .clickable { Log.d("Tag", "Review button pressed") }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val onBackClick: (Unit) -> Unit = {}
    DetailScreenContent(
        offer = offerMock11,
        restaurant = localRestaurantMock1,
        onBackClick = onBackClick
    )
}

@Preview
@Composable
fun ReviewSection_Preview() {
    ReviewSection()
}

@Preview
@Composable
fun RestaurantSection_Preview() {
    RestaurantSection(restaurant = localRestaurantMock1)
}
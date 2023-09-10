package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.offerMock4
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.ui.QRCodeGenerator
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen (viewModel: MainScreenViewModel, offerId: String) {
    val restaurantState by viewModel.stateRestaurants.collectAsState()
    val offer = restaurantState.find { restaurant ->
        restaurant.offers.any { offer -> offer.id == offerId }
    }?.offers?.find { it.id == offerId }

    LaunchedEffect(Unit){
        Log.d("Tag", "🎉 DetailScreen > offer id: $offerId")
        val test = viewModel.getOfferById(offerId) // alternate method to fetch desired offerWithId
    }
    if (offer != null) {
        DetailScreenContent(offer = offer) // try detailState too
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    offer: Offer
){
    Scaffold(
    ) { it ->
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            item {
                Text(
                    text = "Oferta", // tested, pass
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                DetailOfferItem(offer = offer) // show full detail item card
            }
            item { ReviewSection() }
        }
    }
}
@Composable
fun DetailOfferItem(offer: Offer, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
//            .height(750.dp),
//        .fillMaxHeight(), // image doesn't appear using this
            .requiredHeightIn(min(200.dp, 200.dp), max = 650.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8))
    ) {
        Row(modifier = Modifier) {
            Text(text = offer.offerName.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp).weight(1f))
            FavoriteHeart(modifier = Modifier
                .align(Alignment.Bottom)
                .padding(12.dp)
            )
        }
//        Text(text = offer.offerName.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Text(text = "De ${offer.startTime.substringAfter("T").substringBefore(":00Z")} a ${offer.endTime.substringAfter("T").substringBefore(":00Z")}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
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
        Text(text = offer.description.toString(), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        QRCodeGenerator(
            data = offer.id,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun FavoriteHeart(modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            Log.d("Tag","Favorite clicked!")
        }
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
//            tint = if (hero.favorite) Color.Yellow else Color.LightGray,
            modifier = Modifier.size(32.dp)
        )
    }
}
@Composable
fun ReviewSection(modifier: Modifier = Modifier) {
    ElevatedCard (
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0xFFFFF9E8)
            )
    ) {
        Text(text = "Reseñas", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Text(text = "¡Muy buena!", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreenContent(offerMock4)
}

val localOfferSample = LocalOffer(
    id = "123",
    restaurant = LocalRestaurantShortInfo(
        id = "789",
        name = "Tapas Restaurante Ejemplo"
    ),
    offerName = "10 por 1!",
    description = "¡Ben para disfruta la comida! Tenemos 10 por 1 solamente ésta tarde!",
    image = "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300",
    startTime = "1600",
    endTime = "1800",
    postTime = "1500",
)
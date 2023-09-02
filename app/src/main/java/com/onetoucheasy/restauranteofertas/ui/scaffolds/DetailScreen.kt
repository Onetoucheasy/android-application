package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen (viewModel: MainScreenViewModel, id: String) {
    val offerState by viewModel.stateOffers.collectAsState()
    val offer = offerState.find { it.id == id }
    LaunchedEffect(Unit){
        Log.d("Tag","DetailScreen...")
    }
    if (offer != null) {
        DetailScreenContent(offer = offer)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    offer: LocalOffer
){
    Scaffold(
    ) { it ->
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            item {
                Text(
                    text = "Oferta", // tested, pass
//                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                OfferItem(offer = offer) // show full detail item card
            }
        }
    }
}
@Composable
fun OfferItem(offer: LocalOffer, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        Text(text = offer.offerName.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = offer.image,
            contentDescription = offer.description,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = offer.restaurant.name.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Text(text = offer.description.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Text(text = "${offer.startTime} - ${offer.endTime}", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreenContent(offerSample)
}

val offerSample = LocalOffer(
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
package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.mappers.RemoteToLocalMapper
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.ui.QRCodeGenerator
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen (viewModel: MainScreenViewModel, id: String) {
    val offerState by viewModel.stateOffers.collectAsState()
    val offer = offerState.find { it.id == id }
    val remoteToLocalMapper = RemoteToLocalMapper()
    LaunchedEffect(Unit){
        Log.d("Tag", "🎉 DetailScreen > offer id: $id, offer: $offer")
        // TODO: get offer using endpoint http://127.0.0.1:8080/api/offers/id=123...789
    }
    if (offer != null) {
        DetailScreenContent(offer = remoteToLocalMapper.mapLocalOfferToOffers(offer))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    offer: Offers
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
fun DetailOfferItem(offer: Offers, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
//            .height(550.dp)
    ) {
        Text(text = offer.offerName.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        Text(text = "${offer.startTime} - ${offer.endTime}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = offer.image,
            contentDescription = offer.description,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = offer.description.toString(), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        Box(modifier = Modifier.fillMaxSize()
        ) {
            QRCodeGenerator(
                data = offer.id,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .align(Alignment.Center)
            )
        }
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
                ambientColor = Color(0x40000000)
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
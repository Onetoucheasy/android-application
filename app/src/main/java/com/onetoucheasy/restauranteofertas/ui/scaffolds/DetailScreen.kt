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

    LaunchedEffect(Unit){
        Log.d("Tag", "🎉 DetailScreen > offer id: $offerId")
        val test = viewModel.getOfferById(offerId) // alternate method to fetch desired offerWithId
    }
    if (offer != null) {
        DetailScreenContent(
            offer = offer,
            onBackClick = onBackClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    offer: Offer,
    onBackClick: (Unit) -> Unit
){
    Scaffold(
    ) { it ->
        LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            item {
                TopBar(onBackClick = onBackClick)
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
        Text(
            text = "Restaurant Placeholder",
            style = MaterialTheme.typography.bodyLarge,
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
        Text(text = "Cenamos en \"Tapas Bar\" anoche y quedamos completamente encantados. Desde el momento en que entramos, el ambiente fue acogedor, con suaves melodías de jazz llenando el espacio. El menú fue una deliciosa fusión de clásico y contemporáneo, y cada plato demostraba la destreza del chef. El servicio fue impecable, haciendo nuestra noche verdaderamente inolvidable. ¡Altamente recomendado!", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val onBackClick: (Unit) -> Unit = {}
    DetailScreenContent(offerMock11, onBackClick = onBackClick)
}
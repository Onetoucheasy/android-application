package com.onetoucheasy.restauranteofertas.ui.scaffolds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.ui.theme.White
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel, userType: String, onOfferClick: (String)-> Unit = { _->}) {

    val offerList by viewModel.stateOffers.collectAsState()
    val restaurantList by viewModel.stateOffers.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getOffersList()
    }

    fun onOfferFavClicked(offerID: String) {
        //viewModel.updateFavClicked(offerID)
    }

    MainScreenContent(offerList, onOfferClicked= onOfferClick) { offer ->
        onOfferFavClicked(offer)
    }
}

@Composable
fun MainScreenContent(offers: List<LocalOffer>,
                      onOfferClicked: (String) -> Unit,
                      onOfferFavClicked: (String) -> Unit) {

    Column(modifier = Modifier
        .padding(8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth())
        {
            CustomSearchBar( "", screenWidth = 500.dp, Modifier.weight(1f)) { _ -> }
            Box(modifier = Modifier.height(60.dp)) {
                Icon(modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu")
                }
            }
        TabScreen(modifier = Modifier
            .padding(),
            { OffersList(offers)},
            { OffersList(offers)} )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MainScreen_Preview() {
    MainScreenContent()
}*/


@Composable
fun TabScreen(modifier: Modifier = Modifier,
              offers: @Composable() () -> Unit,
              restaurants: @Composable() () -> Unit,) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ofertas", "Restaurantes")

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TabRow(selectedTabIndex = tabIndex,
        containerColor = Color(0xFFFFF9E8),
        contentColor = Color.Black) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> offers()
            1 -> restaurants()
        }
    }
}


@Composable
fun OffersList(offers: List<LocalOffer>){

    if(offers.isNotEmpty()){
        LazyColumn(
            Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(offers,
                key = { it.description}) {
                OfferItem(oferta)
            }
        }
    }
    else{
        //MyCircularProgressIndicator()
    }
}



@Composable
fun OfferItem(offer: LocalOffer, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            ),

        //elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)
        )

    ) {
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
            AsyncImage(
                model = "https://www.equipamientointegraldeoficinas.com/wp-content/uploads/Post-11-6.png",//serie.thumbnail.path + "."+ serie.thumbnail.extension,
                contentDescription = offer.description,
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
            text = offer.offerName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 20.dp, top= 20.dp))
        Text(
            text = "De ${offer.startTime} a ${offer.endTime}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp, top = 10.dp))
    }
}

@Preview
@Composable
fun OfferItem_Preview() {
    OfferItem(LocalOffer("1", restaurant = LocalRestaurantShortInfo("1", "NombreRestaurante"),"2x1 en Carta", "2x1 en toda la carta, excepto postres y bebidas.", "", "14:30", "17:30", ""))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(text: String,
                    screenWidth: Dp,
                    modifier: Modifier,
                 onValueChange: (String) -> (Unit))
{
    TextField(value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(screenWidth * 4.0f / 5 - 50.dp)
            //.fillMaxWidth()
            .background(White)
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
            ,
        trailingIcon = {
            Icon(Icons.Default.Search,
                contentDescription  = stringResource(id = R.string.main_view_search_bar_hint),
                tint = Color.Black)
        },
        placeholder = {
                FormLabel(hint = stringResource(R.string.main_view_search_bar_hint), TextDecoration.None)
        },
        //singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
    )
}

@Preview
@Composable
fun CustomSearchBar_Preview() {
    //CustomSearchBar( "", screenWidth = 500.dp) { _ -> }
}

var oferta = LocalOffer("1",
    restaurant = LocalRestaurantShortInfo(name = "Restaurante Pepito",
        id = "String"),
    "2x1 en Carta",
    "2x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    "")
var oferta2 = LocalOffer("2",
    restaurant = LocalRestaurantShortInfo(name = "Restaurante Pepito",
        id = "String"),
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "19:30",
    "20:30",
    "")


var offerList = mutableListOf<LocalOffer>()

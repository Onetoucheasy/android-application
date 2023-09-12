package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.onetoucheasy.restauranteofertas.R
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalOffer
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offer
import com.onetoucheasy.restauranteofertas.ui.theme.White
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel
import com.onetoucheasy.restauranteofertas.repository.localRestaurantListMock
import com.onetoucheasy.restauranteofertas.repository.offerMutableListMock
import com.onetoucheasy.restauranteofertas.repository.localOfferMock1
import com.onetoucheasy.restauranteofertas.repository.localOfferMock2
import com.onetoucheasy.restauranteofertas.repository.offerMock11
import com.onetoucheasy.restauranteofertas.repository.offerMock12
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    userType: String,
    onOfferClick: (String)-> Unit = { _->},
    onRestaurantClick: (String)-> Unit = { _->}
) {
    val offerList: List<Offer> = emptyList()
    val restaurantList by viewModel.stateRestaurants.collectAsState()
//    val restaurantList = restaurantsSim // mock here, not below

    LaunchedEffect(Unit) {
        viewModel.getRestaurants()
//        viewModel.getOffers() // ⚠️ Error: Expected BEGIN_OBJECT but was BEGIN_ARRAY at path $
    }

    fun onOfferFavClicked(offerID: String) {
        //viewModel.updateFavClicked(offerID) // TODO: to save Favs Offer
    }

    MainScreenContent(
        offerList, // List<Offer>, different than offerList
        restaurantList,
        onOfferClicked = onOfferClick,
        onRestaurantClicked = onRestaurantClick,
    ) { offer ->
        onOfferFavClicked(offer)
    }
}

@Composable
fun MainScreenContent(
    offers: List<Offer>,
    restaurants: List<LocalRestaurant>,
    onOfferClicked: (String) -> Unit,
    onRestaurantClicked: (String) -> Unit,
    onOfferFavClicked: (String) -> Unit)
{
    Column(modifier = Modifier
        .padding(8.dp)) {
        Row(modifier = Modifier
//            .background(Color.Blue) // todo: remove after testing
            .fillMaxWidth())
        {
            CustomSearchBar( "", screenWidth = 500.dp, Modifier.weight(1f)) { _ -> }
            Box(modifier = Modifier.height(60.dp)) {
                Icon(modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White) // todo: change back to white
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu")
            }
        }
        TabSection(
            offersList = {
                OfferTabSection (
                    restaurants = restaurants,
                    offers = offers,
                    onRestaurantClick = onRestaurantClicked,
                    onOfferClick = onOfferClicked
                )
            },
            onOfferClick = onOfferClicked,
            restaurantsList = {
                RestaurantTabSection (
                    restaurants = restaurants,
                    onRestaurantClick = onRestaurantClicked
                )
            },
            onRestaurantClick = onRestaurantClicked
        )
    }
}

@Composable
fun TabSection( // Green
    modifier: Modifier = Modifier,
    restaurantsList: @Composable () -> Unit,
    offersList: @Composable () -> Unit,
    onOfferClick: (String) -> Unit, // needed when called inside MainScreenContent
    onRestaurantClick: (String) -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ofertas", "Restaurantes")

    Column(modifier = Modifier
//        .background(Color.Green)  // todo: remove after testing
        .fillMaxWidth()
        .padding(16.dp)) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color(0xFFFFF9E8),
            contentColor = Color.Black
//        indicator = modifier. // todo: change tab color to yellow
        )
        {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> offersList()
            1 -> restaurantsList()
        }
    }
}

@Composable
fun OfferTabSection(
    restaurants: List<LocalRestaurant>,
    offers: List<Offer>, // todo: remove?
    onOfferClick: (String) -> Unit,
    onRestaurantClick: (String) -> Unit) // todo: remove?
{
    if(restaurants.isNotEmpty()){
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(restaurants) { restaurant ->
                Text(text = restaurant.name, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var offersPack = restaurant.offers
                    items(offersPack, key = { it.hashCode()}) {
                        OfferItem(it, onOfferClick = onOfferClick)
                    }
                }
            }
        }
    } // TODO: Incluir progressview para dar feedback de carga al usuario
    else{
    }
}

@Composable
fun OfferItem(offer: Offer, modifier: Modifier = Modifier, onOfferClick: (String) -> Unit) { // todo: change to UUID
    ElevatedCard(
        modifier = modifier
//            .background(Color.Blue) // todo: remove after debugging
//            .fillMaxWidth()
            .width(300.dp)
            .height(200.dp)
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
            )
            .clickable {
                Log.d("Tag","OfferItem > ElevatedCard > click > \noffer.name: ${offer.offerName}\n${offer.id}")
                onOfferClick(offer.id)
            },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)
        )

    ) {
        Log.d("Tag","      OfferItem offer: $offer")
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
            AsyncImage(
                model = offer.image,
                contentDescription = offer.offerName,
                placeholder = painterResource(R.mipmap.image_resto_example),
                modifier = Modifier
                    .fillMaxWidth()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }, // .fillMaxWidth() or fillMaxSize()??
                contentScale = ContentScale.FillBounds,
            )
            Row() {
                Text( // text on top of image
                    text = offer.offerName,
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
            text = "${offer.startTime.substringAfter("T").substringBefore(":00Z")} hs a ${offer.endTime.substringAfter("T").substringBefore(":00Z")} hs", // TODO: Add to Strings Resources
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp, top = 10.dp))
    }
}


// region Restaurants "tab-clicked" elements...

@Composable // used when "Restaurants" tab selected, showing vert list of Restaurant items
fun RestaurantTabSection(restaurants: List<LocalRestaurant>, onRestaurantClick: (String) -> Unit){ // LocalOffer instead of Sting?

    if(restaurants.isNotEmpty()){
        LazyColumn(
            Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(restaurants, key = { it.hashCode()}) {
                RestaurantItem(it, onRestaurantClick = onRestaurantClick)
            }
        }
    } // TODO: Incluir progressview para dar feedback de carga al usuario
    else{
    }
}

@Composable
fun RestaurantItem(restaurant: LocalRestaurant, modifier: Modifier = Modifier, onRestaurantClick: (String) -> Unit) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .clickable {
                Log.d("Tag","⭐ Restaurant Click! ⭐️ RestaurantItem > ElevatedCard > click > \nrestaurant.name: ${restaurant.name}\n${restaurant.id}")
                onRestaurantClick(restaurant.id) // print is correct
            },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9E8))
    ) {
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
            AsyncImage(
                model = restaurant.picture,
                contentDescription = restaurant.name,
                placeholder = painterResource(R.mipmap.image_resto_example),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Row() {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top= 30.dp))
            }
        }
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 20.dp, top= 20.dp))
    }
}

//endregion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(text: String, screenWidth: Dp, modifier: Modifier, onValueChange: (String) -> (Unit))
{
    TextField(value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(screenWidth * 4.0f / 5 - 50.dp)
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

@Preview(showBackground = true)
@Composable
fun MainScreen_Preview() {
    val onOfferFavClick: (String) -> Unit = {}
    offerMutableListMock.add(offerMock11)
    offerMutableListMock.add(offerMock12)
    MainScreenContent(
        offerMutableListMock,
        localRestaurantListMock,
        { _ -> },
        { _ -> },
        onOfferFavClicked = onOfferFavClick)
}

@Preview
@Composable
fun CustomSearchBar_Preview() {
    CustomSearchBar( "", screenWidth = 500.dp, modifier = Modifier) { _ -> }
}

@Preview
@Composable
fun TabScreen_Preview(
    offers: List<LocalOffer> = listOf(localOfferMock1, localOfferMock2),
    restaurants: List<LocalRestaurant> = localRestaurantListMock
) {
    val offersListMock: @Composable () -> Unit = {}
    val restaurantsList: @Composable () -> Unit = {}
    val onOfferClick: (String) -> Unit = {}
    val onRestaurantClick: (String) -> Unit = {}
    TabSection(
        offersList = offersListMock,
        onOfferClick = onOfferClick,
        restaurantsList = restaurantsList,
        onRestaurantClick = onRestaurantClick
    )
}

@Preview
@Composable
fun OfferItem_Preview() {
    val onOfferClick: (String) -> Unit = {}
    OfferItem(offerMock11, onOfferClick = onOfferClick)
}
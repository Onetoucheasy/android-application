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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
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
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurant
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.repository.remote.response.Restaurant
import com.onetoucheasy.restauranteofertas.ui.theme.Pink40
import com.onetoucheasy.restauranteofertas.ui.theme.White
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    userType: String,
    onOfferClick: (String)-> Unit = { _->},
    onRestaurantClick: (String)-> Unit = { _->}
) { // onOfferClick: (LocalOffer), not id since using memory

    val offerList by viewModel.stateOffers.collectAsState()
    val restaurantList by viewModel.stateRestaurants.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getOffers()
        Log.d("Tag","MainScreen...")
    }

    LaunchedEffect(Unit){
        viewModel.getRestaurants()
    }

    fun onOfferFavClicked(offerID: String) {
        //viewModel.updateFavClicked(offerID) // TODO: to save Favs Offers
    }

    MainScreenContent(
        offerList,
        restaurantList,
        onOfferClicked= onOfferClick,
        onRestaurantClicked = onRestaurantClick,
    ) { offer ->
        onOfferFavClicked(offer)
    }

}

@Composable
fun MainScreenContent(
    offers: List<LocalOffer>,
    restaurants: List<LocalRestaurant>,
    onOfferClicked: (String) -> Unit,
    onRestaurantClicked: (String) -> Unit,
    onOfferFavClicked: (String) -> Unit)
{
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
        // region Previous TabScreen Methods

        // Method 1 - only showed offers for 1 restaurant in vertical list
//        TabScreen(modifier = Modifier
//            .padding(),
//            { OffersList(offers, onOfferClick = onOfferClicked)},
//            { OffersList(offers, onOfferClicked)} )

        // Method 2 - only showed offers for 1 restaurant in horizontal list
//        TabScreen(
//            offersList = { OffersListHorizontal(offers = offers, onOfferClick = onOfferClicked) },
//            restaurantsList = { RestaurantList(restaurants = restaurants, onRestaurantClick = onRestaurantClicked) },
//            onOfferClick = onOfferClicked,
//            onRestaurantClick = onRestaurantClicked
//        )
        //endregion

        // Method 3 - Integrate vertical & horizontal
        TabScreen(
            offersList = {
                RestaurantListMain (
                    restaurants = restaurants,
                    offersListHorizontal = {
                        OffersListHorizontal (
                            offers = offers,
                            onOfferClick = onOfferClicked
                        )
                    },
                    offers = offers,
                    onRestaurantClick = onRestaurantClicked
                )
            },
            onOfferClick = onOfferClicked,
            restaurantsList = {
                RestaurantItemList (
                    restaurants = restaurants,
                    onRestaurantClick = onRestaurantClicked
                )
            },
            onRestaurantClick = onRestaurantClicked
        ) // end TabScreen

        // experiment with this

        RestaurantListMain (
            restaurants = restaurants,
            offersListHorizontal = {
                OffersListHorizontal (
                    offers = offers,
                    onOfferClick = onOfferClicked
                )
            },
            offers = offers,
            onRestaurantClick = onRestaurantClicked
        )
    }
}


@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
//    offersList: List<LocalOffer>,
//    onOfferClick: (String) -> Unit,
//    restaurantsList: List<LocalRestaurant>,
//    onRestaurantClick: (String) -> Unit
    offersList: @Composable () -> Unit,
    onOfferClick: (String) -> Unit,
    restaurantsList: @Composable () -> Unit,
    onRestaurantClick: (String) -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ofertas", "Restaurantes")
//    Log.d("Tag", "TabScreen > Offer: $offers")
//    Log.d("Tag", "TabScreen > restaurants: $restaurants")

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
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
//        when (tabIndex) {
//            0 -> OffersList(offersList, onOfferClick)
//            1 -> RestaurantList(restaurantsList, onRestaurantClick)
//        }
        when (tabIndex) {
            0 -> offersList()
            1 -> restaurantsList()
        }
    }
}


@Composable
fun RestaurantListMain( // main vert scroll, containing horz items. Based on RestaurantItemList
    restaurants: List<LocalRestaurant>, // w List<Offers>
    offers: List<LocalOffer>,
    offersListHorizontal: @Composable () -> Unit,
    onRestaurantClick: (String) -> Unit){ // LocalOffer instead of Sting?

    if(restaurants.isNotEmpty()){
        LazyColumn(
            Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(restaurants, key = { it.id }) { restaurant ->
                RestaurantOfferContainer(
                    restaurant = restaurant,
                    offers = offers,
                    onOfferClick = onRestaurantClick)
            }
        }
    } // TODO: Incluir progressview para dar feedback de carga al usuario
    else{

    }
}

@Composable // card used to contain restaurant containers
fun RestaurantOfferContainer(
    restaurant: LocalRestaurant,
    offers: List<LocalOffer>,
//    offersListHorizontal: () -> Unit,
    onOfferClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    if(restaurants.isNotEmpty()) {
//
//    }
    Column(
        modifier = modifier
            .fillMaxWidth()
//            .height(400.dp)
//            .requiredHeight(100) // no workie
            .wrapContentHeight()
            .shadow( // test color
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
    ) {
        Text(text = restaurant.name.toString(), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(8.dp))
        OffersListHorizontal(offers = offers, onOfferClick = onOfferClick)
    }
}

@Composable // used to show horz scrollable list of offers
fun OffersListHorizontal(offers: List<LocalOffer>, onOfferClick: (String) -> Unit){ // LocalOffer instead of Sting?

    if(offers.isNotEmpty()){
        LazyRow(
            Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(offers,
                key = { it.hashCode()}) {
                OfferItem(it, onOfferClick = onOfferClick)
            }
        }
    } // TODO: Incluir progressview para dar feedback de carga al usuario
    else{

    }
}



@Composable
fun OfferItem(offer: LocalOffer, modifier: Modifier = Modifier, onOfferClick: (String) -> Unit) { // todo: change to UUID
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
            .clickable { onOfferClick(offer.id) },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)
        )

    ) {
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
            AsyncImage(
                model = offer.image,
                contentDescription = offer.description,
                placeholder = painterResource(R.mipmap.image_resto_example),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Row() {
                Text(
                    text = offer.restaurant.name,
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
            text = "De ${offer.startTime} a ${offer.endTime}", // TODO: Add to Strings Resources
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 20.dp, top = 10.dp))
    }
}



// region Restaruantes "tab-clicked" elements...

@Composable // used when "Restaurantes" tab selected, showing vert list of Restaurant items
fun RestaurantItemList(restaurants: List<LocalRestaurant>, onRestaurantClick: (String) -> Unit){ // LocalOffer instead of Sting?

    if(restaurants.isNotEmpty()){
        LazyColumn(
            Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(restaurants,
                key = { it.hashCode()}) {
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
            .clickable { onRestaurantClick(restaurant.id) },
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF9E8)
        )

    ) {
        Box(modifier = Modifier
            .height(15.dp)
            .weight(1f)){
//            AsyncImage(
//                model = restaurant.image, // doesn't exist yet
//                contentDescription = restaurant.description,
//                placeholder = painterResource(R.mipmap.image_resto_example),
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentScale = ContentScale.Crop,
//            )
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



// region PREVIEWS

@Preview(showBackground = true)
@Composable
fun MainScreen_Preview() {
    val onOfferFavClick: (String) -> Unit = {}
    offerMockList.add(offerMock1)
    offerMockList.add(offerMock2)
    MainScreenContent(offerMockList, restaurantMockList, { _ -> }, { _ -> }, onOfferFavClicked = onOfferFavClick)
}

@Preview
@Composable
fun CustomSearchBar_Preview() {
    CustomSearchBar( "", screenWidth = 500.dp, modifier = Modifier) { _ -> }
}

@Preview
@Composable
fun TabScreen_Preview(
    offers: List<LocalOffer> = listOf(offerMock1, offerMock2),
    restaurants: List<LocalRestaurant> = listOf()
) {
    val offersListMock: @Composable () -> Unit = {}
    val restaurantsList: @Composable () -> Unit = {}
    val onOfferClick: (String) -> Unit = {}
    val onRestaurantClick: (String) -> Unit = {}
    TabScreen(
        offersList = offersListMock,
        onOfferClick = onOfferClick,
        restaurantsList = restaurantsList,
        onRestaurantClick = onRestaurantClick
    )
}
@Preview
@Composable
fun RestaurantOfferContainer_Preview () {
    val offers: List<LocalOffer> = listOf(offerMock1, offerMock2)
    val onOfferClick: (String) -> Unit = {}
    RestaurantOfferContainer(
        restaurant = restauranteMock1,
        offers = offers,
        onOfferClick = onOfferClick,
        modifier = Modifier.shadow( // test color
            elevation = 10.dp,
            spotColor = Color(0x00FF0000),
            ambientColor = Color(color = 0x00FF0000))
    )
}
@Preview
@Composable
fun OfferItem_Preview() {
    val onOfferClick: (String) -> Unit = {}
    OfferItem(LocalOffer("1", restaurant = LocalRestaurantShortInfo("1", "Restaurant Name"),"2x1 in Menu", "2x1 in all dishes (desserts and beverages not included).", "", "14:30", "17:30", ""), onOfferClick = onOfferClick)
}
var offerMock1 = LocalOffer("1",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "2x1 en Carta",
    "2x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    "")

var offerMock2 = LocalOffer("2",
    restaurant = LocalRestaurantShortInfo(name = "Restaurant Name",
        id = "String"),
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "19:30",
    "20:30",
    "")

var restauranteMock1 = LocalRestaurant(
    id = "123",
    name = "Restaurante Mock 1",
    type = "Tapas Mock",
    latitude = "40.23",
    longitude = "-4.56",
    openingHour = "1000",
    closingHour = "2359",
    offers = listOf(
        Offers(
            id = "123",
            offerName = "OfferNameMock",
            description = "description mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
)
)
//var offerMock3 = Offer(
//
//)

var offerMockList = mutableListOf<LocalOffer>()

var restaurantMockList = mutableListOf<LocalRestaurant>()

//endregion
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
import com.onetoucheasy.restauranteofertas.repository.local.model.LocalRestaurantShortInfo
import com.onetoucheasy.restauranteofertas.repository.remote.response.Offers
import com.onetoucheasy.restauranteofertas.ui.theme.White
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    userType: String,
    onOfferClick: (String)-> Unit = { _->},
    onRestaurantClick: (String)-> Unit = { _->}
) {
    val offersList: List<Offers> = emptyList()
    val restaurantList by viewModel.stateRestaurants.collectAsState()
//    val restaurantList = restaurantsSim // mock here, not below

    LaunchedEffect(Unit) {
        viewModel.getRestaurants()
//        viewModel.getOffers() // ⚠️ Error: Expected BEGIN_OBJECT but was BEGIN_ARRAY at path $
    }

    fun onOfferFavClicked(offerID: String) {
        //viewModel.updateFavClicked(offerID) // TODO: to save Favs Offers
    }

    MainScreenContent(
        offersList, // List<Offers>, different than offerList
        restaurantList,
        onOfferClicked = onOfferClick,
        onRestaurantClicked = onRestaurantClick,
    ) { offer ->
        onOfferFavClicked(offer)
    }
}

@Composable
fun MainScreenContent(
    offers: List<Offers>,
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
            contentColor = Color.Black)
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
    offers: List<Offers>, // todo: remove?
    onOfferClick: (String) -> Unit,
    onRestaurantClick: (String) -> Unit) // todo: remove?
{
    if(restaurants.isNotEmpty()){
        LazyColumn(
            modifier = Modifier,
//                .background(Color.Red), // todo: remove after testing
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
//                        Log.d("Tag","   offers.count() ${restaurant.offers.count()}")
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
fun OfferItem(offer: Offers, modifier: Modifier = Modifier, onOfferClick: (String) -> Unit) { // todo: change to UUID
    ElevatedCard(
        modifier = modifier
//            .background(Color.Blue) // todo: remove after debugging
//            .fillMaxWidth()
            .width(300.dp)
            .height(200.dp)
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .clickable {
                Log.d("Tag","⭐️ OfferItem > ElevatedCard > click > \noffer.name: ${offer.offerName}\n${offer.id}")
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
            text = "De ${offer.startTime.substringAfter("T").substringBefore(":00Z")} a ${offer.endTime.substringAfter("T").substringBefore(":00Z")}", // TODO: Add to Strings Resources
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

// region PREVIEWS

@Preview(showBackground = true)
@Composable
fun MainScreen_Preview() {
    val onOfferFavClick: (String) -> Unit = {}
    offerMockList.add(offerMock3)
    offerMockList.add(offerMock4)
    MainScreenContent(
        offerMockList,
        restaurantsSim,
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
    offers: List<LocalOffer> = listOf(offerMock1, offerMock2),
    restaurants: List<LocalRestaurant> = listOf()
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
    OfferItem(Offers("1","2x1 in Menu", "2x1 in all dishes (desserts and beverages not included).", "", "14:30", "17:30", ""), onOfferClick = onOfferClick)
}
//endregion

// region MOCK DATA

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

var offerMock3 = Offers(
    "3",
    "3x1 en Carta",
    "3x1 en toda la carta, excepto postres y bebidas.",
    "",
    "14:30",
    "17:30",
    "")

var offerMock4 = Offers(
    "4",
    "40% descuento ahora!",
    "4x1 en toda la carta, excepto postres y bebidas. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
    "2023-08-09T13:00:00Z\"",
    "2023-08-09T15:00:00Z",
    "")

var restauranteMock1 = LocalRestaurant(
    id = "123",
    name = "Restaurante Mock 1",
    type = "Tapas Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
        Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9101",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-1",
            description = "Mock description1-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9102",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-2",
            description = "Mock description1-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
        Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9103",
//            restaurant = LocalRestaurantShortInfo(
//                id = "123",
//                name = "Restaurante Mock 1"
//            ),
            offerName = "OfferNameMock1-3",
            description = "Mock description1-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
)
)

var restauranteMock2 = LocalRestaurant(
    id = "456",
    name = "Restaurante Mock 2",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
        Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9201",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-1",
            description = "Mock description2-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
            Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9202",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-2",
            description = "Mock description2-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
            Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9203",
//            restaurant = LocalRestaurantShortInfo(
//                id = "456",
//                name = "Restaurante Mock 2"
//            ),
            offerName = "OfferNameMock2-3",
            description = "Mock description2-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
    )
)

var restauranteMock4 = LocalRestaurant(
    id = "4",
    name = "Rest Mock 4",
    type = "Type 4",
    picture = "https://images.pexels.com/photos/3421920/pexels-photo-3421920.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.04",
//    longitude = "-4.04",
//    openingHour = "1004",
//    closingHour = "2304",
    offers = listOf(
//        LocalOffer(
            Offers(
            id = "401",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock41",
            description = "Mock desc 41 mock",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        ),
//        LocalOffer(
            Offers(
            id = "402",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock42",
            description = "Mock desc 42 mock",
            image = "https://www.restaurantelua.com/wp-content/uploads/2020/11/01_slider_restaurante_movil.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        ),
//        LocalOffer(
            Offers(
            id = "403",
//            restaurant = LocalRestaurantShortInfo(
//                id = "4",
//                name = "Rest Mock 4"
//            ),
            offerName = "OfferNameMock43",
            description = "Mock desc 43 mock",
            image = "https://www.hotelaiguablava.com/media/restaurante/espacios/restaurante/01a-restaurante-hotel-aigua-blava.jpg",
            startTime = "1604",
            endTime = "1804",
            postTime = "1504"
        )
    )
)
var restauranteMock5 = LocalRestaurant(
    id = "5",
    name = "Rest Mock 5",
    type = "Type 5",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "50.05",
//    longitude = "-5.05",
//    openingHour = "1005",
//    closingHour = "2305",
    offers = listOf(
//        LocalOffer(
            Offers(
            id = "501",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock51",
            description = "Mock desc 51 mock",
            image = "https://www.image51.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        ),
//        LocalOffer(
            Offers(
            id = "502",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock52",
            description = "Mock desc 52 mock",
            image = "https://www.image52.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        ),
//        LocalOffer(
            Offers(
            id = "503",
//            restaurant = LocalRestaurantShortInfo(
//                id = "5",
//                name = "Rest Mock 5"
//            ),
            offerName = "OfferNameMock53",
            description = "Mock desc 53 mock",
            image = "https://www.image53.jpg",
            startTime = "1605",
            endTime = "1805",
            postTime = "1505"
        )
    )
)
var restauranteMock6 = LocalRestaurant(
    id = "6",
    name = "Rest Mock 6",
    type = "Type 6",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "60.06",
//    longitude = "-6.06",
//    openingHour = "1006",
//    closingHour = "2306",
    offers = listOf(
//        LocalOffer(
            Offers(
            id = "601",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock61",
            description = "Mock desc 61 mock",
            image = "https://www.image61.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        ),
//        LocalOffer(
            Offers(
            id = "602",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock62",
            description = "Mock desc 62 mock",
            image = "https://www.image62.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        ),
//        LocalOffer(
            Offers(
            id = "603",
//            restaurant = LocalRestaurantShortInfo(
//                id = "6",
//                name = "Rest Mock 6"
//            ),
            offerName = "OfferNameMock63",
            description = "Mock desc 63 mock",
            image = "https://www.image63.jpg",
            startTime = "1606",
            endTime = "1806",
            postTime = "1506"
        )
    )
)
var restauranteMock3 = LocalRestaurant(
    id = "789",
    name = "Restaurante Mock 3",
    type = "Shushi Mock",
    picture = "https://images.pexels.com/photos/18078297/pexels-photo-18078297/free-photo-of-ciudad-calle-barra-urbano.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
//    latitude = "40.23",
//    longitude = "-4.56",
//    openingHour = "1000",
//    closingHour = "2359",
    offers = listOf(
//        LocalOffer(
            Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9301",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-1",
            description = "Mock description3-1 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
            Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9302",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-2",
            description = "Mock description3-2 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        ),
//        LocalOffer(
            Offers(
            id = "b2e21a5e-958f-4ab8-84fe-7d78b63b9303",
//            restaurant = LocalRestaurantShortInfo(
//                id = "789",
//                name = "Restaurante Mock 3"
//            ),
            offerName = "OfferNameMock3-3",
            description = "Mock description3-3 mock Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ",
            image = "https://www.camarero10.com/wp-content/uploads/2020/02/como-distribuir-un-restaurante.jpg",
            startTime = "2023-08-09T15:00:00Z",
            endTime = "2023-08-09T17:00:00Z",
            postTime = "22023-08-09T11:00:00Z"
        )
    )
)

var restaurantsSim = listOf<LocalRestaurant>(restauranteMock4, restauranteMock5, restauranteMock6)

var offers3to4Sim = listOf<Offers>(offerMock3, offerMock4)
//var offerMockList = mutableListOf<LocalOffer>()
var offerMockList = mutableListOf<Offers>()

var restaurantMockList = mutableListOf<LocalRestaurant>()

// endregion
package com.onetoucheasy.restauranteofertas.ui.scaffolds

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onetoucheasy.restauranteofertas.ui.viewModels.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen (viewModel: MainScreenViewModel, id: String) {
    val offerState by viewModel.stateOffers.collectAsState()
    LaunchedEffect(Unit){
        Log.d("Tag","DetailScreen...")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Oferta Detalle")
                }
            )
        },
        content = {
            GenericList()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericList() {
    val listItems = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(listItems) { item ->
            Text(text = item, modifier = Modifier.padding(8.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailScreenPreview() {
//    DetailScreen()
//}
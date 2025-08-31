package com.foodics.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.foodics.presentation.MenuActions
import com.foodics.presentation.MenuUiState
import models.Product
import utils.sdp
import utils.ssp

@Composable
fun MenuItemsGrid(
    uiState: MenuUiState,
    handelAction: (MenuActions) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemMinWidth = 170.dp
    val calculatedColumns = maxOf(1, (screenWidth / itemMinWidth).toInt())
    val columns = minOf(4, calculatedColumns)

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.sdp),
        horizontalArrangement = Arrangement.spacedBy(12.sdp),
        contentPadding = PaddingValues(5.sdp)
    ) {
        items(uiState.getFilteredProducts() ?: emptyList()) { product ->
            MenuItemCard(product, handelAction)
        }
    }
}

@Composable
private fun MenuItemCard(product: Product, handelAction: (MenuActions) -> Unit) {

    Card(
        shape = RoundedCornerShape(4.sdp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.sdp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                handelAction(MenuActions.AddProduct(product))
            }
            .size(140.sdp)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(77.sdp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(bottomStart = 4.sdp, bottomEnd = 4.sdp)
                    )
                    .padding(8.sdp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(7.sdp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = product.name,
                            fontSize = 9.ssp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Price : ",
                            fontSize = 9.ssp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                            )
                        Text(
                            text = product.price.toString(),
                            fontSize = 9.ssp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = product.description,
                            fontSize = 8.ssp,
                            color = Color.Black,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }

            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {


    MenuItemsGrid(uiState = MenuUiState.getDummyUiState()) {}
}


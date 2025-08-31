package com.foodics.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodics.presentation.MenuActions
import com.foodics.presentation.MenuUiState
import models.Category
import utils.sdp
import utils.ssp

@Composable
fun CategoryTabs(
    uiState: MenuUiState,
    handelAction: (MenuActions) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.sdp),
        horizontalArrangement = Arrangement.spacedBy(16.sdp),
        contentPadding = PaddingValues(horizontal = 16.sdp)
    ) {
        if (uiState.categories != null) {
            itemsIndexed(uiState.categories) { index, category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { handelAction(MenuActions.SetSelectedCategory(category)) }
                ) {
                    Text(
                        text = category.name,
                        fontSize = 10.ssp,
                        fontWeight = if (category.id == uiState.selectedCategory?.id) FontWeight.Bold else FontWeight.Normal,
                        color = if (category.id == uiState.selectedCategory?.id) Color.Black else Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.sdp))
                    Box(
                        modifier = Modifier
                            .height(2.sdp)
                            .width(30.sdp)
                            .background(
                                color = if (category.id == uiState.selectedCategory?.id) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    CategoryTabs(
        uiState = MenuUiState.getDummyUiState(),
        handelAction = { }
    )
}
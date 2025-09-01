package com.foodics.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.foodics.presentation.MenuActions
import com.foodics.presentation.MenuUiState
import com.foodics.presentation.theme.Purple40
import utils.sdp
import utils.ssp

@Composable
fun ViewOrderBar(uiState: MenuUiState, handelAction: (MenuActions) -> Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp)
            .height(40.sdp)
            .clip(RoundedCornerShape(6.sdp))
            .background(Purple40)
            .clickable {

                handelAction(MenuActions.RemoveCart)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Box(
                Modifier
                    .size(20.sdp)
                    .clip(shape = CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    uiState.addedProductList.size.toString(),
                    fontSize = 9.ssp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Text(
                "View Order",
                fontSize = 9.ssp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 10.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {

            Text(
                uiState.getFormattedTotalPrice(),
                fontSize = 9.ssp,
                fontWeight = FontWeight.W500,
                color = Color.White
            )

            Icon(
                modifier = Modifier.size(16.sdp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "spoon",
                tint = Color.White

            )
        }

    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
private fun Preview() {

    Scaffold(bottomBar = {
        Column {
            ViewOrderBar(uiState = MenuUiState.getDummyUiState(), {})
            BottomNavigationBar(uiState = MenuUiState.getDummyUiState()) {}

        }
    }
    ) {


    }

}
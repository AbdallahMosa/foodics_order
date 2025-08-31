package com.foodics.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.foodics.presentation.MenuUiState
import utils.sdp
import utils.ssp

@Composable
fun EmptyScreen(uiState: MenuUiState) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(com.foodics.domain.R.drawable.ic_empty_screen),
            contentDescription = "Empty Image",
            modifier = Modifier.size(300.sdp)

        )
        Spacer(modifier = Modifier.height(10.sdp))
        Text(
            "${uiState.selectedBottomNavItem.title} Screen ",
            fontSize = 12.ssp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center

        )
    }

}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EmptyScreen(uiState = MenuUiState.getDummyUiState())
}
package com.foodics.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.foodics.domain.R
import utils.sdp
import utils.ssp

@Composable
fun MenuTopAppBar(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        TopSection()
        BottomSection()


    }

}

@Composable
private fun TopSection(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            Icon(
                imageVector = Icons.Sharp.AccountCircle,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(12.sdp)
            )
            Text("Abdallah Mosa", fontSize = 10.ssp, color = Color.Gray)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {

            Text("991253", fontSize = 10.ssp)

            Box(
                modifier = Modifier
                    .size(12.sdp)
                    .clip(shape = CircleShape)
                    .background(Color.Green.copy(alpha = 0.5f))

            )
        }
    }
}

@Composable
private fun BottomSection(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "arrow back",
                modifier = Modifier.size(14.sdp)
            )
            Text("Menu", fontSize = 15.ssp, fontWeight = FontWeight.W700)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Icon(
                    modifier = Modifier.size(12.sdp),
                    painter = painterResource(R.drawable.ic_restaurant),
                    contentDescription = "spoon",
                    tint = Color.Black

                )
                Text("03", fontSize = 10.ssp, fontWeight = FontWeight.W500, color = Color.Gray)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Icon(
                    modifier = Modifier.size(12.sdp),
                    painter = painterResource(R.drawable.ic_group),
                    contentDescription = "spoon",
                    tint = Color.Black

                )
                Text("02", fontSize = 10.ssp, fontWeight = FontWeight.W500, color = Color.Gray)
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    MenuTopAppBar()
}
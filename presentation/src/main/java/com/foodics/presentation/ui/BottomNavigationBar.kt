package com.foodics.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.foodics.presentation.MenuActions
import com.foodics.presentation.MenuUiState
import com.foodics.presentation.theme.Purple40
import enums.BottomNavItem
import utils.sdp
import utils.ssp

@Composable
fun BottomNavigationBar(
    uiState: MenuUiState,
    handelAction: (MenuActions) -> Unit,
) {
    NavigationBar(containerColor = Color.White) {
        BottomNavItem.values().forEach { item ->
            NavigationBarItem(
                selected = uiState.selectedBottomNavItem == item,
                onClick = { handelAction(MenuActions.SetSelectedBottomNav(item)) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(15.sdp)
                    )
                },
                label = {
                    Text(text = item.title, fontSize = 7.ssp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    BottomNavigationBar(uiState = MenuUiState.getDummyUiState()) {}

}
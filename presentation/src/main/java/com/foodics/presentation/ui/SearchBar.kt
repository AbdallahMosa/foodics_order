package com.foodics.presentation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodics.presentation.MenuActions
import com.foodics.presentation.MenuUiState
import utils.sdp
import utils.ssp

@Composable
fun MenuSearchBar(
    uiState: MenuUiState,
    handelAction: (MenuActions) -> Unit,
) {
    TextField(
        value = uiState.searchTextField,
        onValueChange = { handelAction(MenuActions.SearchTextFieldChange(it)) },
        placeholder = {
            Text(
                text = "Search for products or categories",
                color = Color.Gray,
                fontSize = 10.ssp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.size(17.sdp).padding(start = 5.sdp)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.sdp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = Color.Gray.copy(0.2f))
            .height(40.sdp)
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    Column(modifier = Modifier.padding(20.sdp)) {

        MenuSearchBar(uiState = MenuUiState.getDummyUiState()) {}
    }


}
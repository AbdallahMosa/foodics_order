package com.foodics.presentation

import models.Product
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreenRoute(viewModel: MenuViewModel = koinViewModel()) {
    MenuScreen(viewModel.uiState, viewModel::handelAction)
}

@Composable
fun MenuScreen(
    uiStateFlow: Flow<MenuUiState>,
    handelAction: (MenuActions) -> Unit
) {
    val uiState by uiStateFlow.collectAsStateWithLifecycle(MenuUiState.getDummyUiState())


    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${uiState.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                        Button(
                            onClick = { }
                        ) {
                            Text("Retry")
                        }
                    }
                }

                else -> {
                    Text(
                        text = "Products (${uiState.products?.size})",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    LazyColumn {
                        items(uiState.products ?: emptyList()) { product ->
                            ProductItem(product = product)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.titleLarge)
        Text(text = product.category.name, style = MaterialTheme.typography.bodySmall)
        Text(text = "$${product.price}", style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    MenuScreen(
        uiStateFlow = flowOf(MenuUiState.getDummyUiState()),
        handelAction = {}
    )
}
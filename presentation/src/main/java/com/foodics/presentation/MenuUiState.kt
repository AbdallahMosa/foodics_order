package com.foodics.presentation

import models.Category
import models.Product

data class MenuUiState(
    val isLoading: Boolean = false,
    val products: List<Product>? = null,
    val categories : List<Category>? = null,
    val error: String? = null
){
    companion object {
        fun getDummyUiState() = MenuUiState(
            products = emptyList(),
            isLoading = false
        )
    }
}

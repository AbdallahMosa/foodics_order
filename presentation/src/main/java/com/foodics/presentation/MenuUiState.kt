package com.foodics.presentation

import enums.BottomNavItem
import models.Category
import models.Product

data class MenuUiState(
    val isLoading: Boolean = false,
    val allProducts: List<Product>? = null,
    val categories: List<Category>? = null,
    val selectedCategory: Category? = null,
    val productsByCategory: Map<String, List<Product>> = emptyMap(),
    val searchTextField: String = "",
    val selectedBottomNavItem: BottomNavItem = BottomNavItem.Tables,
    val addedProductList: List<Product> = emptyList(),
    val error: String? = null,
    val isOnline : Boolean = true
) {

    private val currentProducts: List<Product>?
        get() = selectedCategory?.let { productsByCategory[it.id] } ?: allProducts


    private fun getTotalPrice(): Double {
        return addedProductList.sumOf { it.price }
    }

    fun viewOrderVisibility(): Boolean {
        return addedProductList.isNotEmpty()
    }

    fun getFormattedTotalPrice(): String {
        return "SAR ${"%.2f".format(getTotalPrice())}"
    }

    fun getFilteredProducts(searchQuery: String = this.searchTextField): List<Product>? {
        return  if (searchQuery.isBlank()){
             currentProducts
        }else {
             currentProducts?.filter { product ->
                val query = searchQuery.lowercase().trim()
                query.isEmpty() || product.name.lowercase().contains(query)
            }
        }

    }
    companion object {

        val dummyProducts = listOf(
            Product(
                id = "prod_001",
                category = Category(id = "cat_001", name = "Beverages"),
                name = "Fresh Orange Juice",
                description = "Freshly squeezed orange juice with no added sugar",
                image = "https://images.unsplash.com/photo-1613478223719-2ab802602423?w=400&h=300&fit=crop",
                price = 12.5
            ),
            Product(
                id = "prod_002",
                category = Category(id = "cat_001", name = "Beverages"),
                name = "Iced Coffee",
                description = "Chilled coffee with milk and ice",
                image = "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=400&h=300&fit=crop",
                price = 15.0
            )
        )

        val dummyCategories = listOf(
            Category("cat_001", "Beverages"),
            Category("cat_002", "Appetizers"),
            Category("cat_003", "Main Courses"),
            Category("cat_004", "Desserts"),
            Category("cat_005", "Salads"),
            Category("cat_006", "Breakfast"),
            Category("cat_007", "Soups"),
            Category("cat_008", "Sandwiches"),
            Category("cat_009", "Pizza"),
            Category("cat_010", "Seafood")
        )

        fun getDummyUiState() = MenuUiState(
            allProducts = dummyProducts,
            categories = dummyCategories,
            selectedCategory = dummyCategories.first(),
            isLoading = false
        )
    }
}

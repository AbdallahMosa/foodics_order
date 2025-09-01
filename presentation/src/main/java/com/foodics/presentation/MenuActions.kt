package com.foodics.presentation

import enums.BottomNavItem
import models.Category
import models.Product

sealed interface MenuActions {
     data object GetProduct : MenuActions
     data object GetCategories : MenuActions
     data class SetSelectedCategory(val category: Category?) : MenuActions
     data class SearchTextFieldChange(val text : String) : MenuActions
     data class SetSelectedBottomNav(val bottomNavItem: BottomNavItem): MenuActions
     data class AddProduct(val product: Product) : MenuActions
     data object RemoveCart : MenuActions
     data object RefreshData : MenuActions
}
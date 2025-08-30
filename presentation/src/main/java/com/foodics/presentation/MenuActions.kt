package com.foodics.presentation

sealed interface MenuActions {
     data object GetProduct : MenuActions
     data object GetCategories : MenuActions
}
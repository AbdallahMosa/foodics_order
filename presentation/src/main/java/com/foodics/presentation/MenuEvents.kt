package com.foodics.presentation

sealed interface MenuEvents {
    data object NoEvent : MenuEvents
    data class ShowToast(val message: String) : MenuEvents
}
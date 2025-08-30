package com.foodics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import use_cases.GetCategoriesUseCase
import use_cases.GetProductsUseCase

class MenuViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<MenuEvents>()
    val events = _events.asSharedFlow()


    init {
        getProducts()
        getCategories()
    }

    fun handelAction(actions: MenuActions) {
        when (actions) {
            MenuActions.GetProduct -> {
                getProducts()
            }

            MenuActions.GetCategories -> {
                getCategories()
            }
        }
    }

    private fun getCategories() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val result = kotlin.runCatching {
                getCategoriesUseCase()
            }
            result.onSuccess { categories ->
                _uiState.update {
                    it.copy(
                        categories = categories,
                        isLoading = false,
                        error = null
                    )
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to load products"
                    )
                }
                emitEvent(MenuEvents.ShowToast(exception.message ?: "Failed to load products"))
            }
        }
    }

    private fun getProducts() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val result = kotlin.runCatching {
                getProductsUseCase()
            }
            result.onSuccess { products ->
                _uiState.update {
                    it.copy(
                        products = products,
                        isLoading = false,
                        error = null
                    )
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to load products"
                    )
                }
                emitEvent(MenuEvents.ShowToast(exception.message ?: "Failed to load products"))
            }
        }
    }

    fun emitEvent(event: MenuEvents) {
        viewModelScope.launch {
            _events.emit(event)
        }

    }
}
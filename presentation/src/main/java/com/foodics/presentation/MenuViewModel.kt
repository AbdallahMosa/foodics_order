package com.foodics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enums.BottomNavItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.Category
import models.Product
import network.NetworkMonitor
import use_cases.GetCategoriesUseCase
import use_cases.GetProductsUseCase

class MenuViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    val networkMonitor: NetworkMonitor
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
            is MenuActions.GetProduct -> {
                getProducts()
            }

            is MenuActions.GetCategories -> {
                getCategories()
            }

            is MenuActions.SetSelectedCategory -> {
                setSelectedCategory(actions.category)
            }

            is MenuActions.SearchTextFieldChange -> {
                searchTextFieldChange(actions.text)
            }

            is MenuActions.SetSelectedBottomNav -> {
                setSelectedBottomNav(actions.bottomNavItem)
            }

            is MenuActions.AddProduct -> {
                addProduct(actions.product)

            }

            is MenuActions.RemoveCart -> {
                removeCart()
            }

            is MenuActions.RefreshData -> {
                refreshData()
            }
        }
    }
    private fun refreshData() {
        getProducts()
        getCategories()
    }

    private fun removeCart() {
        _uiState.update {
            it.copy(
                addedProductList = emptyList()
            )
        }
    }

    private fun addProduct(product: Product) {
        _uiState.update {
            it.copy(
                addedProductList = _uiState.value.addedProductList + product
            )
        }
    }

    private fun setSelectedBottomNav(bottomNavItem: BottomNavItem) {
        _uiState.update {
            it.copy(selectedBottomNavItem = bottomNavItem)
        }
    }

    private fun searchTextFieldChange(text: String) {
        _uiState.update {
            it.copy(
                searchTextField = text
            )
        }
    }

    private fun setSelectedCategory(category: Category?) {
        _uiState.update {
            it.copy(
                selectedCategory = category
            )
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
                        selectedCategory = categories.first(),
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
                val productsByCategory = products.groupBy { it.category.id }

                _uiState.update {
                    it.copy(
                        allProducts = products,
                        productsByCategory = productsByCategory,
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
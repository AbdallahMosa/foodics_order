package com.foodics.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.foodics.presentation.theme.Purple40
import com.foodics.presentation.ui.BottomNavigationBar
import com.foodics.presentation.ui.CategoryTabs
import com.foodics.presentation.ui.EmptyScreen
import com.foodics.presentation.ui.MenuItemsGrid
import com.foodics.presentation.ui.MenuSearchBar
import com.foodics.presentation.ui.MenuTopAppBar
import com.foodics.presentation.ui.OfflineIndicatorBar
import com.foodics.presentation.ui.ShimmerEffectView
import com.foodics.presentation.ui.ViewOrderBar
import enums.BottomNavItem
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicatorDefaults
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import network.NetworkMonitor
import org.koin.androidx.compose.koinViewModel
import utils.sdp

@Composable
fun MenuScreenRoute(viewModel: MenuViewModel = koinViewModel()) {

    val networkState by viewModel.networkMonitor.networkState.collectAsStateWithLifecycle(
        NetworkMonitor.NetworkState.Available
    )

    MenuScreen(viewModel.uiState, viewModel::handelAction, networkState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    uiStateFlow: Flow<MenuUiState>,
    handelAction: (MenuActions) -> Unit,
    networkState: NetworkMonitor.NetworkState
) {
    val uiState by uiStateFlow.collectAsStateWithLifecycle(MenuUiState())

    val state = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = {
            handelAction(MenuActions.RefreshData)
        }
    )


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(12.sdp),
                verticalArrangement = Arrangement.spacedBy(10.sdp)

            ) {
                OfflineIndicatorBar(!networkState.isAvailable())
                MenuTopAppBar()
            }

        },
        bottomBar = {
            Column {
                AnimatedVisibility(
                    visible = uiState.viewOrderVisibility(),
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                ) {
                    ViewOrderBar(uiState, handelAction)
                }
                BottomNavigationBar(uiState = uiState, handelAction = handelAction)

            }
        },
        containerColor = Color.Unspecified,
        modifier = Modifier.statusBarsPadding()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(state)
                .padding(16.sdp)
        ) {
            when {
                uiState.isLoading -> {
                    ShimmerEffectView()
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
                            onClick = {
                                handelAction(MenuActions.RefreshData)
                            }
                        ) {
                            Text("Retry")
                        }
                    }
                }

                uiState.selectedBottomNavItem != BottomNavItem.Tables -> {
                    EmptyScreen(uiState)
                }

                else -> {
                    MenuSearchBar(uiState = uiState, handelAction = handelAction)

                    CategoryTabs(uiState = uiState, handelAction = handelAction)
                    Box() {
                        uiState.allProducts?.let {
                            MenuItemsGrid(uiState = uiState, handelAction = handelAction)
                        }
                        PullRefreshIndicator(
                            refreshing = false,
                            state = state,
                            modifier = Modifier
                                .align(Alignment.TopCenter),
                            colors = PullRefreshIndicatorDefaults.colors(
                                contentColor = Purple40
                            )

                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    MenuScreen(
        uiStateFlow = flowOf(MenuUiState.getDummyUiState()),
        handelAction = {},
        networkState = NetworkMonitor.NetworkState.Available
    )
}
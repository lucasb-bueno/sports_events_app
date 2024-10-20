package com.lucasbueno.sportevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasbueno.sportevents.presentation.MainViewModel
import com.lucasbueno.sportevents.presentation.screen.MainScreen

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        uiState = uiState,
        onToggleClick = viewModel::onToggleClick,
        onRefresh = viewModel::getData,
        onFavoriteClick = viewModel::onEventFavoriteClick
    )
}

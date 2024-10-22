package com.lucasbueno.sportevents.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lucasbueno.sportevents.presentation.DataState
import com.lucasbueno.sportevents.presentation.components.HeaderSection
import com.lucasbueno.sportevents.presentation.components.SportsEventsTopBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: DataState,
    onToggleClick: (Boolean, String) -> Unit,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    onRefresh: () -> Unit
) {
    Column(modifier = modifier) {
        SportsEventsTopBar()

        when (uiState) {
            is DataState.Error -> ErrorScreen(uiState = uiState)
            DataState.Loading -> LoadingScreen()
            is DataState.Success -> MainScreenContent(
                uiState = uiState,
                onToggleClick = onToggleClick,
                onRefresh = onRefresh,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, uiState: DataState) {
    val errorState = uiState as? DataState.Error

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val errorMessage = errorState?.message ?: "Unknown Error"
        Text(text = "Error: $errorMessage")
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    uiState: DataState,
    onToggleClick: (Boolean, String) -> Unit,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    onRefresh: () -> Unit
) {
    val successState = uiState as? DataState.Success

    successState?.let { state ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = false),
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            ) {
                val sportsEventsList = state.data.sportsEventsList
                if (sportsEventsList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No Events to Display!", color = Color.White, fontSize = 20.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val favoriteList = state.data.favoriteSportsList
                        items(sportsEventsList) { sport ->
                            sport?.sportId?.let { id ->
                                HeaderSection(
                                    sportId = id,
                                    title = sport.sportName ?: "N/A",
                                    isToggled = favoriteList.any { it.sportId == sport.sportId && it.isFavorite },
                                    onToggleClick = onToggleClick,
                                    activeEvents = sport.activeEvents,
                                    onFavoriteClick = onFavoriteClick,
                                    favoriteEventList = state.data.favoriteEvents
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.lucasbueno.sportevents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasbueno.sportevents.domain.model.FavoriteEvent
import com.lucasbueno.sportevents.domain.model.FavoriteSport
import com.lucasbueno.sportevents.domain.model.Sport
import com.lucasbueno.sportevents.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DataState>(DataState.Loading)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun onToggleClick(isToggled: Boolean, sportId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                mainRepository.updateSportIsFavorite(sportId = sportId, isFavorite = isToggled)
            }.onSuccess {
                println("Added favorite Sport successfully")
                getFavoriteSports()
            }.onFailure {
                println("Failed adding favorite Sport")
            }
        }
    }

    fun onEventFavoriteClick(sportId: String, eventId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            kotlin.runCatching {
                mainRepository.updateFavoriteEvents(
                    sportId = sportId,
                    eventId = eventId,
                    isFavorite = isFavorite
                )
            }.onSuccess {
                println("Added favorite Event successfully")
                getFavoriteEvents()
            }.onFailure {
                println("Failed adding favorite Event")
            }
        }
    }

    private suspend fun getFavoriteSports() {
        viewModelScope.launch {
            runCatching {
                mainRepository.getFavoriteSports()
            }.onSuccess { favoriteList ->
                val successState = (state.value as? DataState.Success)
                successState?.let {
                    _state.value = it.copy(
                        data = it.data.copy(
                            favoriteSportsList = favoriteList
                        )
                    )
                }
            }
        }
    }

    private suspend fun getFavoriteEvents() {
        viewModelScope.launch {
            runCatching {
                mainRepository.getFavoriteEvents()
            }.onSuccess { favoriteEvents ->
                val successState = (state.value as? DataState.Success)
                successState?.let {
                    _state.value = it.copy(
                        data = it.data.copy(
                            favoriteEvents = favoriteEvents
                        )
                    )
                }
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            _state.value = DataState.Loading
            runCatching {
                mainRepository.getSportsEvents()
            }.onSuccess { sportsEventsList ->
                _state.value = DataState.Success(
                    HomeScreenState(
                        sportsEventsList = sportsEventsList
                    )
                )
                getFavoriteSports()
                getFavoriteEvents()
            }.onFailure { error ->
                _state.value = DataState.Error(message = error.message.orEmpty())
            }
        }
    }
}

data class HomeScreenState(
    val sportsEventsList: List<Sport?> = listOf(),
    val favoriteSportsList: List<FavoriteSport> = listOf(),
    val favoriteEvents: List<FavoriteEvent> = listOf()
)

sealed class DataState {
    data object Loading : DataState()
    data class Success(val data: HomeScreenState) : DataState()
    data class Error(val message: String) : DataState()
}
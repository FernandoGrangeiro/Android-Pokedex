package com.fernando.pokedex.presentation

import androidx.lifecycle.SavedStateHandle
import com.fernando.pokedex.domain.usecase.GetPokedexUseCase
import com.fernando.pokedex.domain.usecase.RefreshPokedexUseCase
import com.fernando.pokedex.presentation.mapper.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.krzdabrowski.starter.core.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

private const val HTTP_PREFIX = "http"
private const val HTTPS_PREFIX = "https"

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokedexUseCase: GetPokedexUseCase,
    private val refreshPokedexUseCase: RefreshPokedexUseCase,
    savedStateHandle: SavedStateHandle,
    PokedexInitialState: PokedexUiState,
) : BaseViewModel<PokedexUiState, PokedexUiState.PartialState, PokedexEvent, PokedexIntent>(
    savedStateHandle,
    PokedexInitialState,
) {
    init {
        observePokedex()
    }

    override fun mapIntents(intent: PokedexIntent): Flow<PokedexUiState.PartialState> = when (intent) {
        is PokedexIntent.RefreshPokedex -> refreshPokedex()
        is PokedexIntent.PokedexClicked -> PokedexClicked(intent.uri)
    }

    override fun reduceUiState(
        previousState: PokedexUiState,
        partialState: PokedexUiState.PartialState,
    ): PokedexUiState = when (partialState) {

        is PokedexUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false,
        )

        is PokedexUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            Pokedex = partialState.list,
            isError = false,
        )

        is PokedexUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true,
        )
    }

    private fun observePokedex() = acceptChanges(
        getPokedexUseCase()
            .map { result ->
                result.fold(
                    onSuccess = { PokedexList ->
                        PokedexUiState.PartialState.Fetched(PokedexList.map { it.toPresentationModel() })
                    },
                    onFailure = {
                        PokedexUiState.PartialState.Error(it)
                    },
                )
            }
            .onStart {
                emit(PokedexUiState.PartialState.Loading)
            },
    )

    private fun refreshPokedex(): Flow<PokedexUiState.PartialState> = flow<PokedexUiState.PartialState> {
        refreshPokedexUseCase()
            .onFailure {
                emit(PokedexUiState.PartialState.Error(it))
            }
    }.onStart {
        emit(PokedexUiState.PartialState.Loading)
    }

    private fun PokedexClicked(uri: String): Flow<PokedexUiState.PartialState> = flow {
        if (uri.startsWith(HTTP_PREFIX) || uri.startsWith(HTTPS_PREFIX)) {
            setEvent(PokedexEvent.OpenWebBrowserWithDetails(uri))
        }
    }
}

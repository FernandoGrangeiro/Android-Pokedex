package com.fernando.pokedex.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.fernando.pokedex.presentation.model.PokedexDisplayable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class PokedexUiState(
    val isLoading: Boolean = false,
    val pokedex: List<PokedexDisplayable> = emptyList(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val list: List<PokedexDisplayable>) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}

package com.fernando.pokedex.presentation

sealed class PokedexIntent {
    data object RefreshPokedex : PokedexIntent()
    data class PokedexClicked(val uri: String) : PokedexIntent()
}

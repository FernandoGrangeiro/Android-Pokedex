package com.fernando.pokedex.presentation

sealed class PokedexEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : PokedexEvent()
}

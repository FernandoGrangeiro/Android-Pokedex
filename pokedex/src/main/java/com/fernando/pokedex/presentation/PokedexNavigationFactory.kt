package com.fernando.pokedex.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fernando.pokedex.presentation.composable.PokedexRoute
import eu.krzdabrowski.starter.core.navigation.NavigationDestination.Pokedex
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Inject

class PokedexNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(builder: NavGraphBuilder) {
        builder.composable(Pokedex.route) {
            PokedexRoute()
        }
    }
}

package com.fernando.pokedex.presentation.di

import com.fernando.pokedex.presentation.PokedexNavigationFactory
import com.fernando.pokedex.presentation.PokedexUiState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object PokedexViewModelModule {
    @Provides
    fun provideInitialPokedexUiState(): PokedexUiState = PokedexUiState()
}

@Module
@InstallIn(SingletonComponent::class)
internal interface PokedexSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindPokedexNavigationFactory(factory: PokedexNavigationFactory): NavigationFactory
}

package com.fernando.pokedex.data.di

import com.fernando.pokedex.data.remote.api.PokedexApi
import com.fernando.pokedex.data.repository.PokedexRepositoryImpl
import com.fernando.pokedex.domain.repository.PokedexRepository
import com.fernando.pokedex.domain.usecase.GetPokedexUseCase
import com.fernando.pokedex.domain.usecase.RefreshPokedexUseCase
import com.fernando.pokedex.domain.usecase.getPokedex
import com.fernando.pokedex.domain.usecase.refreshPokedex
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.krzdabrowski.starter.core.network.PokeApiRetrofit
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PokedexModule {

    @Provides
    @Singleton
    fun providePokedexApi(
        @PokeApiRetrofit retrofit: Retrofit,
    ): PokedexApi {
        return retrofit.create(PokedexApi::class.java)
    }

    @Provides
    fun provideGetPokedexUseCase(
        pokedexRepository: PokedexRepository,
    ): GetPokedexUseCase {
        return GetPokedexUseCase {
            getPokedex(pokedexRepository)
        }
    }

    @Provides
    fun provideRefreshPokedexUseCase(
        pokedexRepository: PokedexRepository,
    ): RefreshPokedexUseCase {
        return RefreshPokedexUseCase {
            refreshPokedex(pokedexRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindPokedexRepository(impl: PokedexRepositoryImpl): PokedexRepository
    }
}

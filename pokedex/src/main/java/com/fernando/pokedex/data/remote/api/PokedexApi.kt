package com.fernando.pokedex.data.remote.api

import com.fernando.pokedex.data.remote.model.PokedexResponse
import retrofit2.http.GET

interface PokedexApi {

    @GET("pokemon")
    suspend fun getPokedex(): PokedexResponse
}

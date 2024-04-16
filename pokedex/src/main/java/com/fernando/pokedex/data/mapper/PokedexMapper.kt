package com.fernando.pokedex.data.mapper

import com.fernando.pokedex.data.local.model.PokedexCached
import com.fernando.pokedex.data.remote.model.PokedexResponse
import com.fernando.pokedex.domain.model.Pokedex

fun PokedexResponse.Pokemon.toDomainModel(id: String) = Pokedex(
    id = id,
    name = name,
    baseExperience = 0,
    isDefault = false,
    type = "",
    weight = 0.0,
    imageUrl = "imageUrls.random()",
)

fun PokedexCached.toDomainModel() = Pokedex(
    id = id,
    name = name,
    baseExperience = baseExperience,
    isDefault = isDefault,
    type = type,
    weight = weight,
    imageUrl = "wikiUrl",
)

fun Pokedex.toEntityModel() = PokedexCached(
    id = id,
    name = name,
    baseExperience = baseExperience,
    isDefault = isDefault,
    type = type,
    weight = weight,
    imageUrl = "imageUrls.random()",
)

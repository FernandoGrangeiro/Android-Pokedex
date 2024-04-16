package com.fernando.pokedex.domain.model

data class Pokedex(
    val id: String,
    val name: String,
    val baseExperience: Int,
    val weight: Double,
    val isDefault: Boolean,
    val type: String,
    val imageUrl: String
)
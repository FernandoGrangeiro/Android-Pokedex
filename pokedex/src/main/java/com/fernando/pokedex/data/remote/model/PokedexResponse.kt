package com.fernando.pokedex.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResponse(
    @SerialName("id")
    val id: String = "",

    @SerialName("results")
    val results: List<Pokemon>,
) {
    @Serializable
    data class Pokemon(
        @SerialName("id")
        val id: String = "",

        @SerialName("name")
        val name: String = "",

//        @SerialName("base_experience")
//        val baseExperience: Int = 0,
//
//        @SerialName("weight")
//        val weight: Double?,
//
//        @SerialName("is_default")
//        val isDefault: Boolean?,
//
//        @SerialName("types")
//        val types: List<Type> = emptyList()
    ) {
//        @Serializable
//        data class Type(
//            val name: String,
//            val url: String,
//        )
    }
}

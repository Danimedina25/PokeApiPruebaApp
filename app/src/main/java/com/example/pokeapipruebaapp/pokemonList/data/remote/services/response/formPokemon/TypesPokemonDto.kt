package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon

import com.google.gson.annotations.SerializedName

data class TypesPokemonDto(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypePokemonDto
)
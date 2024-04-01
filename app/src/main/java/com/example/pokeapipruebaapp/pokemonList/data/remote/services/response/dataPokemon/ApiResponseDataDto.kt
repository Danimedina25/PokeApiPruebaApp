package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.dataPokemon

import com.google.gson.annotations.SerializedName

data class ApiResponseDataDto(
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("forms") val forms: List<FormsPokemonDto>
)
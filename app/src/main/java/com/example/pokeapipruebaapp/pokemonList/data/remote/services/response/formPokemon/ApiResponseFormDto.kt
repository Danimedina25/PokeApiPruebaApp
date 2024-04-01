package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon

import com.google.gson.annotations.SerializedName

data class ApiResponseFormDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: SpritesPokemonDto,
    @SerializedName("types") val types: List<TypesPokemonDto>,
)
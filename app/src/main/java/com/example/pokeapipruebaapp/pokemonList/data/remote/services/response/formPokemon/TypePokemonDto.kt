package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon

import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypesModel
import com.google.gson.annotations.SerializedName

data class TypePokemonDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
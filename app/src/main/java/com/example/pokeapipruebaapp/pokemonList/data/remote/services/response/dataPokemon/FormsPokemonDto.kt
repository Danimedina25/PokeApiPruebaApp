package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.dataPokemon

import com.google.gson.annotations.SerializedName

data class FormsPokemonDto(
    @SerializedName("name") val name:String,
    @SerializedName("url") val url:String
)
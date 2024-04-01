package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.listOfPokemons

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

fun ResultDto.getIdPokemon(): Int{
    val urlArray = this.url.split("/")
    return urlArray.get(urlArray.lastIndex-1).toInt()
}
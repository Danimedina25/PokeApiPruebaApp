package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
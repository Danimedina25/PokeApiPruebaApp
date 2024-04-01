package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response

import com.google.gson.annotations.SerializedName

data class ApiResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<ResultDto>
)
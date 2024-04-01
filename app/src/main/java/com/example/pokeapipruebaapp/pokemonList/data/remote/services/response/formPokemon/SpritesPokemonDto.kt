package com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon

import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.google.gson.annotations.SerializedName

data class SpritesPokemonDto(
    @SerializedName("back_default") val back_default: String,
    @SerializedName("front_default") val front_default: String,
)


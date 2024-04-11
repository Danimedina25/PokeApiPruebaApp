package com.example.pokeapipruebaapp.pokemonList.data.mappers

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.dataPokemon.ApiResponseDataDto
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel



fun ApiResponseDataDto.toDomain() = PokemonDataModel(
    height = height,
    weight = weight
)

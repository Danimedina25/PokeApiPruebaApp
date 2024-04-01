package com.example.pokeapipruebaapp.pokemonList.data.mappers

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.dataPokemon.ApiResponseDataDto
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormsModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypeModel


fun ApiResponseDataDto.toDomain() = PokemonDataModel(
    height = height,
    weight = weight
)

package com.example.pokeapipruebaapp.pokemonList.data.mappers

import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon.ApiResponseFormDto
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon.SpritesPokemonDto
import com.example.pokeapipruebaapp.pokemonList.data.remote.services.response.formPokemon.TypePokemonDto
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonTypesListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypeModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypesModel


fun ApiResponseFormDto.toDomain() = PokemonFormModel(
    id,
    name,
    sprites.toDomain(),
    this.toListOfTypesDomain()
)

fun SpritesPokemonDto.toDomain() = SpritesModel(
    back_default = back_default,
    front_default = front_default
)

fun TypePokemonDto.toDomain() = TypeModel(
    url,
    name
)


fun ApiResponseFormDto.toListOfTypesDomain(): PokemonTypesListModel {
    val listOfTypes = types.mapIndexed { _, type ->
        TypesModel(
            slot = type.slot,
            type = type.type.toDomain()
        )
    }

    return PokemonTypesListModel(listOfTypes)
}
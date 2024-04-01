package com.example.pokeapipruebaapp.pokemonList.domain.model

data class PokemonFormModel(
    val id: Int,
    val name: String,
    val sprites: SpritesModel,
    val types: List<TypesModel>,
)
package com.example.pokeapipruebaapp.pokemonList.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonTypesListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypesModel

@Entity(tableName = "poke_api_form_table")
data class PokeApiFormEntity(
    @PrimaryKey(autoGenerate = true)
    val idPokemon: Int,
    val name: String,
    val sprites: SpritesModel,
    val types: PokemonTypesListModel,
)

fun PokeApiFormEntity.toDomain() = PokemonFormModel(
    idPokemon, name, sprites, types
)

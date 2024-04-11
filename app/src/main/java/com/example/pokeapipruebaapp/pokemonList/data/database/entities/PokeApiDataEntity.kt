package com.example.pokeapipruebaapp.pokemonList.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel

@Entity(tableName = "poke_api_data_table")
data class PokeApiDataEntity(
    @PrimaryKey(autoGenerate = true)
    val idPokemon: Int = 0,
    val height: Int,
    val weight: Int
)

fun PokeApiDataEntity.toDomain() = PokemonDataModel(
    height, weight
)

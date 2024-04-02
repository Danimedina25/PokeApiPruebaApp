package com.example.pokeapipruebaapp.pokemonList.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poke_api_favorites_table")
data class PokeApiFavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_pokemon: Int,
    val name_pokemon: String
)



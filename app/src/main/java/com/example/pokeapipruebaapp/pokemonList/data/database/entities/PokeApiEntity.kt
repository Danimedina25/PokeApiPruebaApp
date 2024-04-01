package com.example.pokeapipruebaapp.pokemonList.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonModel

@Entity(tableName = "poke_api_table")
data class PokeApiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listOfPokemons: PokemonListModel
)


fun PokeApiEntity.toDomain(): List<PokemonModel> {
    val listOfPokemons = listOfPokemons.listOfPokemons.mapIndexed { _, pokemon ->
        PokemonModel(
            name = pokemon.name,
            url = pokemon.url
        )
    }
    return listOfPokemons
}


package com.example.pokeapipruebaapp.pokemonList.data.database.converters

import androidx.room.TypeConverter
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.google.gson.Gson
import java.util.Date

class MyTypeConverters {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }
    @TypeConverter
    fun fromListOfPokemonsToJson(listOfPokemons: PokemonListModel): String {
        return Gson().toJson(listOfPokemons)
    }
    @TypeConverter
    fun fromJSONToListOfPokemons(json: String): PokemonListModel {
        return Gson().fromJson(json,PokemonListModel::class.java)
    }
}
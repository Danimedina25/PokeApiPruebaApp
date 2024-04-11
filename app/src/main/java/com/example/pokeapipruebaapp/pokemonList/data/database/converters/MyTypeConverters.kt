package com.example.pokeapipruebaapp.pokemonList.data.database.converters

import androidx.room.TypeConverter
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonTypesListModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.SpritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.TypesModel
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
    @TypeConverter
    fun fromSpritesToJson(spritesModel: SpritesModel): String {
        return Gson().toJson(spritesModel)
    }
    @TypeConverter
    fun fromJSONToSprites(json: String): SpritesModel {
        return Gson().fromJson(json, SpritesModel::class.java)
    }
    @TypeConverter
    fun fromListOfTypesToJson(listOfTypes: PokemonTypesListModel): String {
        return Gson().toJson(listOfTypes)
    }
    @TypeConverter
    fun fromJSONToListOfTypes(json: String):PokemonTypesListModel {
        return Gson().fromJson(json,PokemonTypesListModel::class.java)
    }
}
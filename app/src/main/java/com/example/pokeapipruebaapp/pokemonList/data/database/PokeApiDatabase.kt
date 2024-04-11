package com.example.pokeapipruebaapp.pokemonList.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokeapipruebaapp.pokemonList.data.database.converters.MyTypeConverters
import com.example.pokeapipruebaapp.pokemonList.data.database.dao.PokeApiDao
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiDataEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFavoritesEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFormEntity

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [PokeApiEntity::class,  PokeApiFavoritesEntity::class, PokeApiDataEntity::class, PokeApiFormEntity::class], version = 3)
abstract class PokeApiDatabase: RoomDatabase() {
    abstract fun getPokeApiDao(): PokeApiDao
}
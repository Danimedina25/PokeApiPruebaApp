package com.example.pokeapipruebaapp.pokemonList.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokeapipruebaapp.pokemonList.data.database.converters.MyTypeConverters
import com.example.pokeapipruebaapp.pokemonList.data.database.dao.PokeApiDao
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiEntity
import com.example.pokeapipruebaapp.pokemonList.data.database.entities.PokeApiFavoritesEntity

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [PokeApiEntity::class, PokeApiFavoritesEntity::class], version = 1)
abstract class PokeApiDatabase: RoomDatabase() {
    abstract fun getPokeApiDao(): PokeApiDao
}
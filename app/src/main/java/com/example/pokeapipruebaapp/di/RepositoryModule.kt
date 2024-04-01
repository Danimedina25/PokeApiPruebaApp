package com.example.openweatherapp.di

import com.example.pokeapipruebaapp.pokemonList.data.remote.repository.PokeApiPruebaRepositoryImpl
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokeApiPruebaRepository(
        pokeApiPruebaRepositoryImpl: PokeApiPruebaRepositoryImpl
    ): PokeApiPruebaRepository

}
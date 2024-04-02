package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import com.example.pokeapipruebaapp.pokemonList.domain.model.PokeApiFavoritesModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(pokeApiFavoritesModel: PokeApiFavoritesModel): Long {
        return repository.addToFavorites(pokeApiFavoritesModel)
    }
}
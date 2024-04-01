package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import com.example.pokeapipruebaapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListOfPokemonsUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): NetworkResult<PokemonListModel> {
        return repository.getPokemons(offset, limit)
    }
}
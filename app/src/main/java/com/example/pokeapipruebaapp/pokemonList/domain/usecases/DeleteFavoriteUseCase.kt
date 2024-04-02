package com.example.pokeapipruebaapp.pokemonList.domain.usecases

import com.example.pokeapipruebaapp.pokemonList.domain.repository.PokeApiPruebaRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val repository: PokeApiPruebaRepository) {

    suspend operator fun invoke(idPokemon:Int): Int {
        return repository.deleteToFavorites(idPokemon)
    }
}
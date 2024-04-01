package com.example.pokeapipruebaapp.pokemonList.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetListOfPokemonsUseCase
import com.example.pokeapipruebaapp.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListOfPokemonsViewModel @Inject constructor(
    private val getListOfPokemonsUseCase: GetListOfPokemonsUseCase
) : ViewModel() {

    private val _getPokemonsResult = MutableLiveData<NetworkResult<PokemonListModel>>(
        NetworkResult.Success(PokemonListModel(emptyList())))
    val getPokemonsResult: LiveData<NetworkResult<PokemonListModel>>
        get() = _getPokemonsResult

    private val _getPokemonsErrorMessage = MutableLiveData<String>()
    val getPokemonsErrorMessage: LiveData<String>
        get() = _getPokemonsErrorMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        getPokemons()
    }

    fun getPokemons() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getListOfPokemonsUseCase(0, 25)
            Log.d("result", Gson().toJson(result))
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getPokemonsResult.value = result

            _isLoading.value = false
        }
    }

}
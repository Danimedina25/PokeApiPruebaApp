package com.example.pokeapipruebaapp.pokemonList.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetDataPokemonUseCase
import com.example.pokeapipruebaapp.pokemonList.domain.usecases.GetFormPokemonUseCase
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
    private val getListOfPokemonsUseCase: GetListOfPokemonsUseCase,
    private val getDataPokemonUseCase: GetDataPokemonUseCase,
    private val getFormPokemonUseCase: GetFormPokemonUseCase
) : ViewModel() {

    private val _getPokemonsResult = MutableLiveData<PokemonListModel>()
    val getPokemonsResult: LiveData<PokemonListModel>
        get() = _getPokemonsResult

    private val _listOfItemModel = MutableLiveData<List<ItemModel>>(emptyList())
    val listOfItemModel: LiveData<List<ItemModel>>
        get() = _listOfItemModel

    private val _getDataPokemonResult = MutableLiveData<NetworkResult<PokemonDataModel>>()
    val getDataPokemonResult: LiveData<NetworkResult<PokemonDataModel>>
        get() = _getDataPokemonResult

    private val _getFormPokemonResult = MutableLiveData<PokemonFormModel>()
    val getFormPokemonResult: LiveData<PokemonFormModel>
        get() = _getFormPokemonResult

    private val _getPokemonsErrorMessage = MutableLiveData<String>()
    val getPokemonsErrorMessage: LiveData<String>
        get() = _getPokemonsErrorMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        getPokemons()
    }

    fun addItemModel(itemModel: ItemModel){
        Log.d("addItemModel", Gson().toJson(itemModel))
        _listOfItemModel.value = _listOfItemModel.value!! + itemModel
    }

    fun getListOfItemModel(): List<ItemModel>{
        Log.d("getListOfItemModel", Gson().toJson(_listOfItemModel.value!!) )
        return _listOfItemModel.value!!
    }

    fun getPokemons() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getListOfPokemonsUseCase(0, 25)
            Log.d("resultViewModel", Gson().toJson(result.data))
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getPokemonsResult.value = result.data!!

            _isLoading.value = false
        }
    }
    fun getDataPokemon(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getDataPokemonUseCase(id)
            Log.d("result2 ${id}", Gson().toJson(result))
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getDataPokemonResult.value = result
                //getFormPokemon(id)

            _isLoading.value = false
        }
    }
    fun getFormPokemon(id: Int, name:String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getFormPokemonUseCase(id)
            Log.d("result3"+ "," + id.toString() , Gson().toJson(result) )
            if(!result.message.isNullOrEmpty())
                _getPokemonsErrorMessage.value = result.message!!
            else
                _getFormPokemonResult.value = result.data!!

                val itemModel = ItemModel(
                    id,
                    result.data!!.sprites.front_default,
                    name
                )
                Log.d("itemModel", Gson().toJson(itemModel))
                addItemModel(itemModel)
            _isLoading.value = false
        }
    }


}
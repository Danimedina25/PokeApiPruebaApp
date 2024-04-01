package com.example.pokeapipruebaapp.pokemonList.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.RecyclerViewAdapter
import com.example.pokeapipruebaapp.databinding.FragmentListOfPokemonsBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.ui.viewmodel.ListOfPokemonsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListOfPokemonsFragment : Fragment() {

    private val listOfPokemonsViewModel: ListOfPokemonsViewModel by viewModels()
    private var _binding: FragmentListOfPokemonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var listOfPokemons:PokemonListModel
    private lateinit var listOfItemModel: List<ItemModel>
    private var currentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOfPokemonsViewModel.getPokemons()

        // getting the recyclerview by its id
        recyclerview = binding.recyclerView

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        setupObservers()
    }

    private fun setupObservers(){
        listOfPokemonsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.loader.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }else{
                binding.loader.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })
        listOfPokemonsViewModel.getPokemonsResult.observe(viewLifecycleOwner, Observer { it ->
            it!!.listOfPokemons.forEach {
                if(currentId < 24){
                    currentId++
                    listOfPokemonsViewModel.callToGetFormPokemonForGetImage(it.id, it.name)
                }
            }

        })

        listOfPokemonsViewModel.getDataPokemonResult.observe(viewLifecycleOwner, Observer {
            //listOfPokemons.listOfPokemons.get(currentId).data = it.data!!
        })

        listOfPokemonsViewModel.getFormPokemonResult.observe(viewLifecycleOwner, Observer {
            if(currentId.equals(24)){
                adapter = RecyclerViewAdapter(listOfPokemonsViewModel.getListOfItemModel(), R.color.gray_light, R.color.green, requireContext().getDrawable(R.drawable.profile)!!, ::onClickPokemon
                )
                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }
        })

    }

    private fun onClickPokemon(id: Int){
        listOfPokemonsViewModel.getDataPokemon(id)
    }
}
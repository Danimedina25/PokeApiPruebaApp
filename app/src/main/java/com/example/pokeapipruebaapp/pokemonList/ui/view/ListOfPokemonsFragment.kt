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
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.RecyclerViewAdapter
import com.example.pokeapipruebaapp.databinding.FragmentListOfPokemonsBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.pokemonListModeltoItemModel
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
        listOfPokemonsViewModel.getPokemonsResult.observe(viewLifecycleOwner, Observer {
            Log.d("result", Gson().toJson(it))
            // This loop will create 20 Views containing
            // the image with the count of view
            // This will pass the ArrayList to our Adapter
            adapter = RecyclerViewAdapter(it.data!!.pokemonListModeltoItemModel(), R.color.gray_light, R.color.green, requireContext().getDrawable(R.drawable.profile)!! )

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
        })
    }
}
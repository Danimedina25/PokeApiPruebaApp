package com.example.pokeapipruebaapp.pokemonList.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.ItemModelAdapter
import com.example.pokeapipruebaapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.ui.view.adapters.ItemTypeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ItemTypeAdapter
    private lateinit var pokemonDataModel: PokemonDataModel
    private lateinit var pokemonFormModel: PokemonFormModel
    private val navigationArgs : PokemonDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonDataModel = navigationArgs.pokemonData
        pokemonFormModel = navigationArgs.pokemonForm

        recyclerview = binding.recyclerView
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        setData()
    }

    private fun setData(){
        binding.textName.text = pokemonFormModel.name.capitalize()
        Glide
            .with(requireContext())
            .load(pokemonFormModel.sprites.front_default)
            .placeholder(R.drawable.profile)
            .centerCrop()
            .into(binding.imagePokemon);
        binding.textHeight.text = "${pokemonDataModel.height} pies"
        binding.textWeight.text = "${pokemonDataModel.weight} libras"
        adapter = ItemTypeAdapter(pokemonFormModel.types)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}
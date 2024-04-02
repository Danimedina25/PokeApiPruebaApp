package com.example.pokeapipruebaapp.pokemonList.ui.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokeapipruebaapp.ItemModel
import com.example.pokeapipruebaapp.R
import com.example.pokeapipruebaapp.adapter.RecyclerViewAdapter
import com.example.pokeapipruebaapp.databinding.FragmentListOfPokemonsBinding
import com.example.pokeapipruebaapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonDataModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonFormModel
import com.example.pokeapipruebaapp.pokemonList.domain.model.PokemonListModel
import com.example.pokeapipruebaapp.pokemonList.ui.viewmodel.ListOfPokemonsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
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
        setData()
    }

    private fun setData(){
        binding.textName.text = pokemonFormModel.name.toString().uppercase()
        Glide
            .with(requireContext())
            .load(pokemonFormModel.sprites.front_default)
            .placeholder(R.drawable.profile)
            .centerCrop()
            .into(binding.imagePokemon);
        binding.textHeight.text = "${pokemonDataModel.height} pies"
        binding.textWeight.text = "${pokemonDataModel.weight} libras"
    }
}
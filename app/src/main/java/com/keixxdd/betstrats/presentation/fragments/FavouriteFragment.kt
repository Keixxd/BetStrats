package com.keixxdd.betstrats.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keixxdd.betstrats.R
import com.keixxdd.betstrats.databinding.FragmentFavouriteBinding
import com.keixxdd.betstrats.presentation.adapters.StrategyListAdapter
import com.keixxdd.betstrats.presentation.viewmodels.MainActivityViewmodel

class FavouriteFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val viewmodel by activityViewModels<MainActivityViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StrategyListAdapter(viewmodel)

        (activity as AppCompatActivity).setSupportActionBar(binding.favToolbar)
        binding.favToolbar.setTitle(R.string.fav_title)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.favRV.apply{
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }

        viewmodel.getAllStrategies().observe(viewLifecycleOwner, { favList ->
            if(favList.count{ it.isFavorite } == 0){
                binding.favEmptyText.visibility = View.VISIBLE
            }else{
                binding.favEmptyText.visibility = View.GONE
            }
            adapter.setFavorite(favList)
        })

    }

}
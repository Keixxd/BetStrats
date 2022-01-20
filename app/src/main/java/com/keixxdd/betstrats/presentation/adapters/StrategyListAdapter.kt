package com.keixxdd.betstrats.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keixxdd.betstrats.R
import com.keixxdd.betstrats.databinding.StrategyListItemBinding
import com.keixxdd.betstrats.domain.module.Strategy
import com.keixxdd.betstrats.presentation.fragments.FavouriteFragmentDirections
import com.keixxdd.betstrats.presentation.fragments.HomeFragmentDirections
import com.keixxdd.betstrats.presentation.viewmodels.MainActivityViewmodel
import java.lang.NullPointerException

class StrategyListAdapter(private val viewmodel: MainActivityViewmodel): RecyclerView.Adapter<StrategyListAdapter.StrategyListViewholder>() {

    private var strategyList = emptyList<Strategy>()

    inner class StrategyListViewholder(private val binding: StrategyListItemBinding, val parent: ViewGroup): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {

                Glide.with(root).load(R.drawable.betting_strategy_picture)
                    .into(strategyImage)

                strategyName.text = strategyList[adapterPosition].name

                showMoreButton.setOnClickListener {
                    when(it.findNavController().currentDestination?.id){
                        R.id.homeFragment -> {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(strategyList[adapterPosition])
                            it.findNavController().navigate(action)
                        }
                        R.id.favouriteFragment -> {
                            val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailsFragment(strategyList[adapterPosition])
                            it.findNavController().navigate(action)
                        }
                    }
                }

                favouriteButton.isChecked = strategyList[adapterPosition].isFavorite

                favouriteButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewmodel.addStrategyToFavourite(strategyList[adapterPosition])
                        strategyList[adapterPosition].isFavorite = true
                    } else {
                        val item = strategyList[adapterPosition]
                        viewmodel.deleteStrategyFromFavourite(item)
                        strategyList[adapterPosition].isFavorite = false
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrategyListViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StrategyListItemBinding.inflate(layoutInflater, parent, false)
        return StrategyListViewholder(binding, parent)
    }

    override fun onBindViewHolder(holder: StrategyListViewholder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = strategyList.size

    fun setData(list: List<Strategy>) {
        try {
            strategyList = list
            notifyDataSetChanged()
        }catch (e: NullPointerException){
        }
    }

    fun setFavorite(list: List<Strategy>){
        try{
            strategyList = list.filter{ it.isFavorite }
            notifyDataSetChanged()
        }catch (e: NullPointerException){

        }
    }

    fun fillFav(list: List<Strategy>){
        if(!list.isNullOrEmpty())
            strategyList.forEach {
                if (list.contains(it)) {
                    it.isFavorite = true
                }
            }
    }
}
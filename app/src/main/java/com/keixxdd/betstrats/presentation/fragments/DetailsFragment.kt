package com.keixxdd.betstrats.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.keixxdd.betstrats.R
import com.keixxdd.betstrats.databinding.FragmentDetailsBinding
import com.keixxdd.betstrats.presentation.viewmodels.MainActivityViewmodel

class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewmodel by activityViewModels<MainActivityViewmodel>()

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle(R.string.details_title)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.stratDesc.text = args.strategy.description
        binding.stratTitle.text = args.strategy.name

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_favourites -> {
                if(args.strategy.isFavorite){
                    item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                    args.strategy.isFavorite = false
                    viewmodel.deleteStrategyFromFavourite(args.strategy)
                    Toast.makeText(context, "Removed from favorite!", Toast.LENGTH_LONG).show()
                }else{
                    item.setIcon(R.drawable.favourite_drawable_24dp)
                    args.strategy.isFavorite = true
                    viewmodel.addStrategyToFavourite(args.strategy)
                    Toast.makeText(context, "Added to favorite!", Toast.LENGTH_LONG).show()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_home_menu, menu)
        if (args.strategy.isFavorite){
            menu[0].setIcon(R.drawable.ic_baseline_favorite_24)
        }else{
            menu[0].setIcon(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}
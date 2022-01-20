package com.keixxdd.betstrats.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keixxdd.betstrats.R
import com.keixxdd.betstrats.databinding.FragmentHomeBinding
import com.keixxdd.betstrats.presentation.adapters.StrategyListAdapter
import com.keixxdd.betstrats.presentation.viewmodels.MainActivityViewmodel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel by activityViewModels<MainActivityViewmodel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        binding.homeToolbar.inflateMenu(R.menu.fragment_home_menu)
        binding.homeToolbar.setTitle(R.string.app_name)

        val adapter = StrategyListAdapter(viewmodel)

        binding.homeRv.apply {
            layoutManager = LinearLayoutManager(context)
            binding.homeRv.adapter = adapter
        }

        viewmodel.getAllStrategies().observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        /*viewmodel.getDataObservable().observe(viewLifecycleOwner, { dataList ->
            adapter.setData(dataList)
        })*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_favourites -> {
                findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.homeRv.adapter?.notifyDataSetChanged()
    }
}
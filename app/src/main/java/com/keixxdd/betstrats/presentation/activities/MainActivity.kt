package com.keixxdd.betstrats.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.keixxdd.betstrats.R
import com.keixxdd.betstrats.domain.module.Strategy
import com.keixxdd.betstrats.presentation.viewmodels.MainActivityViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateDB()

    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).popBackStack()
        return true
    }

    private fun populateDB(){
        if(File(this.dataDir, "databases").listFiles().isNullOrEmpty()){
            val strategiesNames = resources.getStringArray(R.array.strategy_names)
            val strategyDesc = resources.getStringArray(R.array.strategy_descriptions)
            val strategies = mutableListOf<Strategy>()

            for (i in strategiesNames.indices){
                strategies.add(Strategy(i, strategiesNames[i], "", strategyDesc[i], false))
            }

            strategies.forEach {
                viewModel.addStrategyToFavourite(it)
            }

            viewModel.getDataObservable().postValue(strategies)
        }else{
            viewModel.getAllStrategies()
        }
    }
}

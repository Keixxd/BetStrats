package com.keixxdd.betstrats.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keixxdd.betstrats.domain.module.Strategy
import com.keixxdd.betstrats.domain.usecases.AddStrategyUsecase
import com.keixxdd.betstrats.domain.usecases.DeleteStrategyUsecase
import com.keixxdd.betstrats.domain.usecases.GetStrategiesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewmodel @Inject constructor(
    private val addStrategyUsecase: AddStrategyUsecase,
    private val deleteStrategyUsecase: DeleteStrategyUsecase,
    private val getStrategiesUsecase: GetStrategiesUsecase
): ViewModel(){

    private val _data: MutableLiveData<List<Strategy>> = MutableLiveData()

    fun getDataObservable(): MutableLiveData<List<Strategy>> = _data

    fun addStrategyToFavourite(strategy: Strategy){
        viewModelScope.launch (Dispatchers.IO) {
            addStrategyUsecase.invoke(strategy)
        }
    }

    fun deleteStrategyFromFavourite(strategy: Strategy){
        viewModelScope.launch (Dispatchers.IO) {
            deleteStrategyUsecase.invoke(strategy)
        }
    }

    fun getAllStrategies(): LiveData<List<Strategy>>{
        return getStrategiesUsecase.invoke()
    }
}
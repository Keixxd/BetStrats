package com.keixxdd.betstrats.domain.usecases

import androidx.lifecycle.LiveData
import com.keixxdd.betstrats.data.repository.FavouriteDatabaseRepository
import com.keixxdd.betstrats.domain.module.Strategy
import javax.inject.Inject

class GetStrategiesUsecase @Inject constructor(
    private val repository: FavouriteDatabaseRepository
){
    operator fun invoke(): LiveData<List<Strategy>>{
        return repository.getStrategies()
    }
}
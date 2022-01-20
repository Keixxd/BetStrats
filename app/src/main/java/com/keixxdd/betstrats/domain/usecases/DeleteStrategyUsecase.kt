package com.keixxdd.betstrats.domain.usecases

import com.keixxdd.betstrats.data.repository.FavouriteDatabaseRepository
import com.keixxdd.betstrats.domain.module.Strategy
import javax.inject.Inject

class DeleteStrategyUsecase @Inject constructor(
    private val repository: FavouriteDatabaseRepository
) {

    suspend operator fun invoke(
        strategy: Strategy
    ){
        repository.deleteFromFavourite(strategy)
    }

}
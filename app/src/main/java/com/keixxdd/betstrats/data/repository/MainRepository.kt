package com.keixxdd.betstrats.data.repository

import androidx.lifecycle.LiveData
import com.keixxdd.betstrats.domain.module.Strategy

interface MainRepository {

    fun getStrategies(): LiveData<List<Strategy>>

    suspend fun deleteFromFavourite(strategy: Strategy)

    suspend fun addToFavourite(strategy: Strategy)
}
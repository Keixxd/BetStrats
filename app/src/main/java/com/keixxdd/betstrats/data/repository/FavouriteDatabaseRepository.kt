package com.keixxdd.betstrats.data.repository

import androidx.lifecycle.LiveData
import com.keixxdd.betstrats.data.database.FavouriteDao
import com.keixxdd.betstrats.domain.module.Strategy
import javax.inject.Inject

class FavouriteDatabaseRepository @Inject constructor(
    private val appDao: FavouriteDao
): MainRepository {
    override fun getStrategies(): LiveData<List<Strategy>> {
        return appDao.getAllRecords()
    }

    override suspend fun deleteFromFavourite(strategy: Strategy) {
        return appDao.removeFromFavorite(strategy)
    }

    override suspend fun addToFavourite(strategy: Strategy) {
        return appDao.addRecord(strategy)
    }
}
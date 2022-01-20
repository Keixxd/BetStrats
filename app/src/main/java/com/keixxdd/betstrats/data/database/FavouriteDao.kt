package com.keixxdd.betstrats.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.keixxdd.betstrats.domain.module.Strategy

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM strats_favourite")
    fun getAllRecords(): LiveData<List<Strategy>>

    @Insert(onConflict = REPLACE)
    suspend fun addRecord(strategy: Strategy)

    @Update
    suspend fun removeFromFavorite(strategy: Strategy)

}
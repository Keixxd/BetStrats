package com.keixxdd.betstrats.di

import android.content.Context
import com.keixxdd.betstrats.data.database.FavouriteDao
import com.keixxdd.betstrats.data.database.FavouriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getAppDB(@ApplicationContext context: Context): FavouriteDatabase = FavouriteDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun getDao(db: FavouriteDatabase): FavouriteDao = db.getDao()

}
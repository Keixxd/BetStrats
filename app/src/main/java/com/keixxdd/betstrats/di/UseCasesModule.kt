package com.keixxdd.betstrats.di

import com.keixxdd.betstrats.data.repository.FavouriteDatabaseRepository
import com.keixxdd.betstrats.domain.usecases.AddStrategyUsecase
import com.keixxdd.betstrats.domain.usecases.DeleteStrategyUsecase
import com.keixxdd.betstrats.domain.usecases.GetStrategiesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideAddStrategyUsecase(repository: FavouriteDatabaseRepository) = AddStrategyUsecase(repository)

    @ViewModelScoped
    @Provides
    fun provideDeleteStrategyUsecase(repository: FavouriteDatabaseRepository) = DeleteStrategyUsecase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetStrategiesUsecase(repository: FavouriteDatabaseRepository) = GetStrategiesUsecase(repository)

}
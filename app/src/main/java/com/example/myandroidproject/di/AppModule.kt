package com.example.myandroidproject.di

import com.example.myandroidproject.core.domain.usecase.DataInteractor
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import com.example.myandroidproject.presentation.viewmodel.IMainViewModel
import com.example.myandroidproject.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideDataUseCase(dataInteractor: DataInteractor): DataUseCase
}
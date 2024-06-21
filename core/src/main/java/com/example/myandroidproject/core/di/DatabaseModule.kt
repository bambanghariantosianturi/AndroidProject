package com.example.myandroidproject.core.di

import android.content.Context
import androidx.room.Room
import com.example.myandroidproject.core.data.source.local.room.DataDao
import com.example.myandroidproject.core.data.source.local.room.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase = Room.databaseBuilder(
        context,
        MainDatabase::class.java, "data.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: MainDatabase): DataDao = database.dataDao()
}
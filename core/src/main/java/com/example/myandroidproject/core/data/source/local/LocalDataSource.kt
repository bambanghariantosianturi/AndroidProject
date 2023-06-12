package com.example.myandroidproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.source.local.entity.GenreEntity
import com.example.myandroidproject.core.data.source.local.room.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun getGenreMovie(): LiveData<List<GenreEntity>> = userDao.getGenreMovie()

    fun insertGenreData(genreData: List<GenreEntity>) = userDao.insertGenreData(genreData)
}
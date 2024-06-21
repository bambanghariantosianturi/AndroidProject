package com.example.myandroidproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.room.DataDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dataDao: DataDao) {

    fun getGenreMovie(): LiveData<List<DataEntity>> = dataDao.getAllData()

    fun insertGenreData(dataEntity: List<DataEntity>) = dataDao.insertData(dataEntity)
}
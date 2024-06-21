package com.example.myandroidproject.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myandroidproject.core.data.source.local.entity.DataEntity

@Dao
interface DataDao {
    @Query("SELECT * FROM data")
    fun getAllData(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<DataEntity>)
}
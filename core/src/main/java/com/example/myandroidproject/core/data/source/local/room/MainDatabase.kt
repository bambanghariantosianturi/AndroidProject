package com.example.myandroidproject.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.entity.IntTypeConverter

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao
}
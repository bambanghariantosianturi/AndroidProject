package com.example.myandroidproject.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "data", primaryKeys = ["name"])
data class DataEntity (
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url")
    val url: String
)
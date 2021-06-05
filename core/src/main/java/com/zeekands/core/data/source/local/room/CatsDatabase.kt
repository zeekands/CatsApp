package com.zeekands.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zeekands.core.data.source.local.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1, exportSchema = false)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDao
}
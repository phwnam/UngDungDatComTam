package com.xuong.ungdungdatcomtam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class CategoryDB : RoomDatabase() {

    abstract fun categoryDAO(): CategoryDAO

    companion object {
        @Volatile
        private var INSTANCE: CategoryDB? = null

        fun getInstance(context: Context): CategoryDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, CategoryDB::class.java, "category_db"
                    ).build()
                }
                return instance
            }
        }
    }
}
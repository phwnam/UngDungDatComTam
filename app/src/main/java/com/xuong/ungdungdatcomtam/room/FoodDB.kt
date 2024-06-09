package com.xuong.ungdungdatcomtam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.model.entities.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
 abstract class FoodDB : RoomDatabase() {

    abstract fun foodDAO(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDB? = null

        fun getInstance(context: Context): FoodDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, FoodDB::class.java, "food.db"
                    ).build()
                }
                return instance
            }
        }
    }

}
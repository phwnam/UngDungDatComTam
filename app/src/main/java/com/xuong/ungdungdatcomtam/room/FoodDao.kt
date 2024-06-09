package com.xuong.ungdungdatcomtam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.model.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert
    suspend fun addFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM FoodEntity")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Delete
    suspend fun deleteFood(foodEntity: FoodEntity)

    @Update
    suspend fun updateFood(foodEntity: FoodEntity)

}
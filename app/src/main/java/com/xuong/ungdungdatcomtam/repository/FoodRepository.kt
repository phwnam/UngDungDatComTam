package com.xuong.ungdungdatcomtam.repository

import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.model.entities.FoodEntity
import com.xuong.ungdungdatcomtam.room.CategoryDB
import com.xuong.ungdungdatcomtam.room.FoodDB


class FoodRepository(private val foodDB: FoodDB) {
    suspend fun addFoodToRoom(foodEntity: FoodEntity) {
        foodDB.foodDAO().addFood(foodEntity)
    }

    fun getAllFoods() = foodDB.foodDAO().getAllFood()

    suspend fun deleteFoodFromRoom(foodEntity: FoodEntity) {
        foodDB.foodDAO().deleteFood(foodEntity)
    }

    suspend fun updateFood(foodEntity: FoodEntity) {
        foodDB.foodDAO().updateFood(foodEntity)
    }
}
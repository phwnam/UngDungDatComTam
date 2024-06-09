package com.xuong.ungdungdatcomtam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.model.entities.FoodEntity
import com.xuong.ungdungdatcomtam.repository.FoodRepository
import com.xuong.ungdungdatcomtam.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FoodViewModel(private val foodRepository: FoodRepository): ViewModel() {


    fun addFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.addFoodToRoom(foodEntity)
        }
    }

    val foods = foodRepository.getAllFoods()

    fun deleteFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.deleteFoodFromRoom(foodEntity)
        }
    }

    fun updateFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.updateFood(foodEntity)
        }
    }

}
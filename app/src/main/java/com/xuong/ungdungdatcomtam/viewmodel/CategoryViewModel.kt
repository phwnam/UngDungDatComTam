package com.xuong.ungdungdatcomtam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.repository.Repository
import kotlinx.coroutines.launch

class CategoryViewModel(val repository: Repository): ViewModel() {
    fun addCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.addCategoryToRoom(category)
        }
    }

    val categories = repository.getAllCategories()

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.deleteCategoryFromRoom(category)
        }
    }

    fun updateCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.updateCategory(category)
        }
    }
}
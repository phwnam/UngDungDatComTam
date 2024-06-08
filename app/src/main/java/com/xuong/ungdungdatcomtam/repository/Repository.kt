package com.xuong.ungdungdatcomtam.repository

import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.room.CategoryDB


class Repository(val categoryDB: CategoryDB) {
    suspend fun addCategoryToRoom(categoryEntity: CategoryEntity) {
        categoryDB.categoryDAO().addCategory(categoryEntity)
    }

    fun getAllCategories() = categoryDB.categoryDAO().getAllCategories()

    suspend fun deleteCategoryFromRoom(categoryEntity: CategoryEntity) {
        categoryDB.categoryDAO().deleteCategory(categoryEntity)
    }

    suspend fun updateCategory(categoryEntity: CategoryEntity) {
        categoryDB.categoryDAO().updateCategory(categoryEntity)
    }
}
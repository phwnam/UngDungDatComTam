package com.xuong.ungdungdatcomtam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert
    suspend fun addCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Delete
    suspend fun deleteCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)
}
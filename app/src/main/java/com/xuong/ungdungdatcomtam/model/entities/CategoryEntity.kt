package com.xuong.ungdungdatcomtam.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) var cid: Int = 0,
    @ColumnInfo(name = "categoryName") var categoryName: String?,
)
package com.xuong.ungdungdatcomtam.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
//    foreignKeys = [ForeignKey(
//        entity = CategoryEntity::class,
//        parentColumns = ["cid"],
//        childColumns = ["categoryId"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "foodName") var foodName: String?,
    @ColumnInfo(name = "price") var price: Int?,
    @ColumnInfo(name = "imagePath") var imagePath: String?,
    @ColumnInfo(name = "categoryId") var categoryId: String?
)
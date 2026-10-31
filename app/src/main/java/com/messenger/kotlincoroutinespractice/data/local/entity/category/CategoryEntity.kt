package com.messenger.kotlincoroutinespractice.data.local.entity.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.messenger.kotlincoroutinespractice.domain.model.category.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val icon: String? = null,
    val isActive: Boolean = true
)

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = icon,
        isActive = isActive
    )
}
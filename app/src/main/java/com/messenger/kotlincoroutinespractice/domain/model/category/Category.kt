package com.messenger.kotlincoroutinespractice.domain.model.category

import com.messenger.kotlincoroutinespractice.data.local.entity.category.CategoryEntity

data class Category(
    val id: Long,
    val name: String,
    val icon: String?,
    val isActive: Boolean
)

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        icon = icon,
        isActive = isActive
    )
}
package com.messenger.kotlincoroutinespractice.domain.repository

import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    suspend fun insertCategory(category: Category): Long
}
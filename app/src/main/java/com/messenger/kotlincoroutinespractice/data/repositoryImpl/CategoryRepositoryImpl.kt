package com.messenger.kotlincoroutinespractice.data.repositoryImpl

import com.messenger.kotlincoroutinespractice.data.local.dao.CategoryDao
import com.messenger.kotlincoroutinespractice.data.local.entity.category.toDomain
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.category.toEntity
import com.messenger.kotlincoroutinespractice.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category.toEntity())
    }
}

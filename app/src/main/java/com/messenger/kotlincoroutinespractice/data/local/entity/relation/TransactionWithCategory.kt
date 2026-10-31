package com.messenger.kotlincoroutinespractice.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.messenger.kotlincoroutinespractice.data.local.entity.category.CategoryEntity
import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.TransactionEntity

data class TransactionWithCategory(
    @Embedded
    val transaction: TransactionEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)

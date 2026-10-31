package com.messenger.kotlincoroutinespractice.domain.model.transaction

import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.TransactionEntity
import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.TransactionTypeEntity

data class Transaction(
    val id: Long,
    val categoryId: Long,
    val amount: Long,
    val description: String?,
    val date: Long,
    val type: TransactionType
)

fun Transaction.toEntity() : TransactionEntity {
    return TransactionEntity(
        id = id,
        categoryId = categoryId,
        amount = amount,
        description = description,
        date = date,
        type = type.toEntity()
    )
}

fun TransactionType.toEntity(): TransactionTypeEntity {
    return when (this) {
        TransactionType.EXPENSE -> TransactionTypeEntity.EXPENSE
        TransactionType.INCOME -> TransactionTypeEntity.INCOME
    }
}
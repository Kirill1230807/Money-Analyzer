package com.messenger.kotlincoroutinespractice.data.local.entity.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.messenger.kotlincoroutinespractice.data.local.entity.category.CategoryEntity
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("categoryId")
    ]
    )
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val categoryId: Long,
    val amount: Long,
    val description: String?,
    val date: Long,
    val type: TransactionTypeEntity
)

fun TransactionEntity.toDomain() : Transaction {
    return Transaction(
        id = id,
        categoryId = categoryId,
        amount = amount,
        description = description,
        date = date,
        type = type.toDomain()
    )
}

fun TransactionTypeEntity.toDomain(): TransactionType {
    return when (this) {
        TransactionTypeEntity.EXPENSE -> TransactionType.EXPENSE
        TransactionTypeEntity.INCOME -> TransactionType.INCOME
    }
}
package com.messenger.kotlincoroutinespractice.domain.repository

import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>>
    suspend fun insertTransaction(
        transaction: Transaction
        )
}
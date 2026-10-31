package com.messenger.kotlincoroutinespractice.data.repositoryImpl

import com.messenger.kotlincoroutinespractice.data.local.dao.TransactionDao
import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.toDomain
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.toEntity
import com.messenger.kotlincoroutinespractice.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map { transactionEntities ->
            transactionEntities.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertTransaction(
        transaction: Transaction
    ) {
        val transaction = transaction.toEntity()
        transactionDao.insertTransaction(transaction)
    }

}
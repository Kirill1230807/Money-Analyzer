package com.messenger.kotlincoroutinespractice.domain.usecases

import com.messenger.kotlincoroutinespractice.data.local.dao.TransactionDao
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThreeLatestTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> {
        return transactionRepository.getThreeLatestTransactions()
    }
}
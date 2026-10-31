package com.messenger.kotlincoroutinespractice.domain.usecases

import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTotalIncomeUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<Long> {
        return transactionRepository.getTransactions().map { transactions ->
            transactions.filter { it.type == TransactionType.INCOME }
                .sumOf { it.amount }
        }
    }
}
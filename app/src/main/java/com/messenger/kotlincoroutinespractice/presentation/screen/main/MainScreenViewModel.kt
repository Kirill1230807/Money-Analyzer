package com.messenger.kotlincoroutinespractice.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.domain.usecases.GetCategoriesUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetThreeLatestTransactionsUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetTotalExpenseUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetTotalIncomeUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetTotalSumUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetTransactionsUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.InsertCategoryUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.InsertTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val transactions: List<Transaction> = emptyList(),
    val categories: List<Category> = emptyList(),
    val totalIncome: Long = 0L,
    val totalExpense: Long = 0L,
    val balance: Long = 0L,
    val isAddTransactionDialogVisible: Boolean = false,
    val isAddCategoryDialogVisible: Boolean = false,
    val threeLatestTransaction: List<Transaction> = emptyList()
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getTotalExpenseUseCase: GetTotalExpenseUseCase,
    private val getTotalIncomeUseCase: GetTotalIncomeUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val threeTransactionsUseCase: GetThreeLatestTransactionsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase
): ViewModel() {

    private val isAddTransactionDialogVisible = MutableStateFlow(false)
    private val isAddCategoryDialogVisible = MutableStateFlow(false)

    val uiState: StateFlow<MainUiState> = combine(
        getTransactionsUseCase(),
        getCategoriesUseCase(),
        getTotalIncomeUseCase(),
        getTotalExpenseUseCase(),
        combine(
            threeTransactionsUseCase(),
            combine(
                isAddTransactionDialogVisible,
                isAddCategoryDialogVisible
            ) { tx, cat -> tx to cat }
        ) { latest, dialog -> latest to dialog }
    ) { transactions, categories, totalIncome, totalExpense, extra ->

        val (latestTransaction, dialogs) = extra
        val (isAddTxVisible, isAddCatVisible) = dialogs
        MainUiState(
            transactions = transactions,
            categories = categories,
            totalIncome = totalIncome,
            totalExpense = totalExpense,
            balance = totalIncome - totalExpense,
            isAddTransactionDialogVisible = isAddTxVisible,
            isAddCategoryDialogVisible = isAddCatVisible,
            threeLatestTransaction = latestTransaction
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState()
    )

    fun showAddTransactionDialog() {
        isAddTransactionDialogVisible.value = true
    }

    fun dismissAddTransactionDialog() {
        isAddTransactionDialogVisible.value = false
    }

    fun showAddCategoryDialog() {
        isAddCategoryDialogVisible.value = true
    }

    fun dismissAddCategoryDialog() {
        isAddCategoryDialogVisible.value = false
    }

    fun addCategory(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            insertCategoryUseCase(
                Category(
                    id = 0,
                    name = name.trim(),
                    icon = null,
                    isActive = true
                )
            )
            dismissAddCategoryDialog()
        }
    }

    fun addTransaction(
        amount: Long,
        categoryId: Long,
        description: String?,
        type: TransactionType
    ) {
        if (amount <= 0 || categoryId <= 0) return
        viewModelScope.launch {
            insertTransactionUseCase(
                Transaction(
                    id = 0,
                    categoryId = categoryId,
                    amount = amount,
                    description = description?.ifBlank { null },
                    date = System.currentTimeMillis(),
                    type = type
                )
            )
            dismissAddTransactionDialog()
        }
    }
}

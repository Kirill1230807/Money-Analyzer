package com.messenger.kotlincoroutinespractice.presentation.screen.transactionsHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.usecases.GetCategoriesUseCase
import com.messenger.kotlincoroutinespractice.domain.usecases.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HistoryUiState(
    val transactions: List<Transaction> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    val uiState: StateFlow<HistoryUiState> =
        combine(
            getTransactionsUseCase(),
            getCategoriesUseCase()
        ){ transactions, categories ->
            HistoryUiState(
                transactions = transactions,
                categories = categories,
                isLoading = false
            )
        }.catch { exception ->
            emit(
                HistoryUiState(
                    isLoading = false,
                    error = exception.message
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HistoryUiState()
        )

}
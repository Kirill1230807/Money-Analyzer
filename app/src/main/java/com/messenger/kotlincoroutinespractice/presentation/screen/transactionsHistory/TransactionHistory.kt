package com.messenger.kotlincoroutinespractice.presentation.screen.transactionsHistory

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTheme
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.TransactionColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionHistory(
    viewModel: TransactionHistoryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TransactionHistoryContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryContent(
    uiState: HistoryUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Історія транзакцій",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when {
                uiState.isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Помилка завантаження",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(uiState.transactions) { transaction ->
                            TransactionItemBlock(transaction, categories = uiState.categories)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItemBlock(transaction: Transaction, categories: List<Category>) {
    val description = transaction.description
    val isIncome = transaction.type == TransactionType.INCOME
    val isDark = isSystemInDarkTheme()
    val amountColor = if (isIncome) {
        TransactionColors.incomeColor(isDark)
    } else {
        TransactionColors.expenseColor(isDark)
    }
    val icon = if (isIncome) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward
    val formattedDate = remember(transaction.date) {
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date(transaction.date))
    }
    val category = categories.firstOrNull { category -> category.id == transaction.categoryId }
    val categoryName = category?.name ?: "Без категорії"
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(amountColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = amountColor
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )

                if (!description.isNullOrBlank()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Text(
                text = if (isIncome) "${transaction.amount} ₴" else "-${transaction.amount} ₴",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = amountColor
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Theme")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Theme")
@Composable
private fun TransactionHistoryPreview() {
    val sampleTransactions = listOf(
        Transaction(
            id = 1,
            categoryId = 2,
            amount = 15000,
            description = "Аванс за місяць",
            date = System.currentTimeMillis() - 86400000,
            type = TransactionType.INCOME
        ),
        Transaction(
            id = 2,
            categoryId = 1,
            amount = 850,
            description = "Покупки в супермаркеті",
            date = System.currentTimeMillis() - 3600000,
            type = TransactionType.EXPENSE
        ),
        Transaction(
            id = 3,
            categoryId = 1,
            amount = 320,
            description = "Кава та випічка",
            date = System.currentTimeMillis(),
            type = TransactionType.EXPENSE
        )
    )

    val sampleCategories = listOf(
        Category(id = 1, name = "Продукти", icon = null, isActive = true),
        Category(id = 2, name = "Зарплата", icon = null, isActive = true)
    )

    AppTheme {
        TransactionHistoryContent(
            uiState = HistoryUiState(
                isLoading = false,
                error = null,
                transactions = sampleTransactions,
                categories = sampleCategories
            ),
            onBackClick = {}
        )
    }
}

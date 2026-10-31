package com.messenger.kotlincoroutinespractice.presentation.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ThreeLatestTransactionBlock(
    transactions: List<Transaction>?,
    categories: List<Category>,
    onHistoryClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    if (transactions.isNullOrEmpty()) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Створіть нову транзакцію")
            }
        }
    } else {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Історія транзакцій",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                transactions.forEach { transaction ->
                    TransactionRow(transaction, categories)
                }
                OutlinedButton(
                    onClick = onHistoryClick,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Text("Переглянути історію транзакцій")
                }
            }
        }
    }
}

@Composable
fun TransactionRow(transaction: Transaction, categories: List<Category>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val category = categories.first { category -> category.id == transaction.categoryId }
        val categoryName = category.name
        val description = transaction.description
        val isIncome = transaction.type == TransactionType.INCOME
        val isDark = isSystemInDarkTheme()
        val amountColor = if (isIncome) {
            if (isDark) Color(0xFF81C784) else Color(0xFF2E7D32)
        } else {
            if (isDark) Color(0xFFE57373) else Color(0xFFC62828)
        }
        val icon = if (isIncome) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward
        val formattedDate = remember(transaction.date) {
            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date(transaction.date))
        }

        Row(
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge
                )

                if (!description.isNullOrBlank()) {
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = formattedDate,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Text(
                text = if (isIncome) "${transaction.amount} ₴" else "-${transaction.amount} ₴",
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = amountColor
            )
        }
    }
}

@Preview(name = "Block preview")
@Composable
private fun ThreeLatestTransactionBlockPreview() {
    val sampleTransactions = listOf(
        Transaction(
            id = 1,
            categoryId = 2,
            amount = 15000,
            description = "Аванс за місяць",
            date = System.currentTimeMillis(),
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
            amount = 850,
            description = "Шашлик",
            date = System.currentTimeMillis() - 3600000,
            type = TransactionType.EXPENSE
        )
    )

    val sampleCategories = listOf(
        Category(id = 1, name = "Продукти", icon = null, isActive = true),
        Category(id = 2, name = "Зарплата", icon = null, isActive = true)
    )
    AppTheme {
        ThreeLatestTransactionBlock(
            transactions = sampleTransactions,
            categories = sampleCategories,
            onHistoryClick = {},
            onTransactionClick = {}
        )
    }
}

@Preview(name = "Empty lists preview")
@Composable
private fun ThreeLatestTransactionEmptyBlockPreview() {
    AppTheme {
        ThreeLatestTransactionBlock(
            transactions = emptyList(),
            categories = emptyList(),
            onHistoryClick = {},
            onTransactionClick = {}
        )
    }
}
package com.messenger.kotlincoroutinespractice.presentation.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTheme
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.TransactionColors

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onNavigateToTransactionHistory: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        uiState = uiState,
        onAddTransactionClick = viewModel::showAddTransactionDialog,
        onDismissTransactionDialog = viewModel::dismissAddTransactionDialog,
        onAddCategoryClick = viewModel::showAddCategoryDialog,
        onDismissCategoryDialog = viewModel::dismissAddCategoryDialog,
        onCreateCategory = viewModel::addCategory,
        onCreateTransaction = viewModel::addTransaction,
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToTransactionHistory = onNavigateToTransactionHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    uiState: MainUiState = MainUiState(),
    onAddTransactionClick: () -> Unit = {},
    onDismissTransactionDialog: () -> Unit = {},
    onAddCategoryClick: () -> Unit = {},
    onDismissCategoryDialog: () -> Unit = {},
    onCreateCategory: (String) -> Unit = {},
    onCreateTransaction: (amount: Long, categoryId: Long, description: String?, type: TransactionType) -> Unit = { _, _, _, _ -> },
    onNavigateToSettings: () -> Unit,
    onNavigateToTransactionHistory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expense Analyzer",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Налаштування"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TotalAmountCard(
                title = "Баланс",
                amount = uiState.balance
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Блок з показанням скільки грошей надійшло
                SummaryCard(
                    modifier = Modifier.weight(1f),
                    title = "Надійшло",
                    amount = uiState.totalIncome,
                    isIncome = true
                )

                // Блок з показанням скільки грошей витрачено
                SummaryCard(
                    modifier = Modifier.weight(1f),
                    title = "Витрачено",
                    amount = uiState.totalExpense,
                    isIncome = false
                )
            }

            // Кнопки для дій
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onAddTransactionClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Додати транзакцію",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Додати транзакцію",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                OutlinedButton(
                    onClick = onAddCategoryClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = "Створити категорію",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Створити категорію",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            // Відображення останні 3 транзакції
            ThreeLatestTransactionBlock(
                transactions = uiState.threeLatestTransaction,
                categories = uiState.categories,
                onHistoryClick = onNavigateToTransactionHistory
            )
        }
    }

    // Dialog для створення категорії
    if (uiState.isAddCategoryDialogVisible) {
        AddCategoryDialog(
            onDismiss = onDismissCategoryDialog,
            onConfirm = onCreateCategory
        )
    }

    // Dialog для додавання транзакцій
    if (uiState.isAddTransactionDialogVisible) {
        AddTransactionDialog(
            categories = uiState.categories,
            onDismiss = onDismissTransactionDialog,
            onConfirm = onCreateTransaction,
            onOpenAddCategory = {
                onDismissTransactionDialog()
                onAddCategoryClick()
            }
        )
    }
}

@Composable
fun TotalAmountCard(
    modifier: Modifier = Modifier,
    title: String,
    amount: Long
) {
    val backgroundColor = TransactionColors.balanceBadge
    val textColor = TransactionColors.balanceText

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(contentColor = backgroundColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor,
                    fontWeight = FontWeight.Medium
                )

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "₴",
                        style = MaterialTheme.typography.headlineSmall,
                        color = textColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Text(
                text = "$amount ₴",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
private fun SummaryCard(
    modifier: Modifier = Modifier,
    title: String,
    amount: Long,
    isIncome: Boolean
) {
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isIncome) {
        TransactionColors.incomeContainer(isDark)
    } else {
        TransactionColors.expenseContainer(isDark)
    }

    val contentColor = if (isIncome) {
        TransactionColors.incomeColor(isDark)
    } else {
        TransactionColors.expenseColor(isDark)
    }

    val icon = if (isIncome) {
        Icons.Default.ArrowDownward
    } else {
        Icons.Default.ArrowUpward
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(contentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = contentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = "${if (isIncome && amount > 0) "+" else if (!isIncome && amount > 0) "-" else ""}$amount ₴",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        }
    }
}

@Composable
private fun AddCategoryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var categoryName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Створити категорію",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = {
                        categoryName = it
                        isError = false
                    },
                    label = { Text("Назва категорії", style = MaterialTheme.typography.bodyMedium) },
                    singleLine = true,
                    isError = isError,
                    modifier = Modifier.fillMaxWidth()
                )

                if (isError) {
                    Text(
                        text = "Введіть назву категорії",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (categoryName.isBlank()) {
                        isError = true
                    } else {
                        onConfirm(categoryName)
                    }
                }
            ) {
                Text("Зберегти", style = MaterialTheme.typography.labelLarge)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати", style = MaterialTheme.typography.labelLarge)
            }
        }
    )
}

@Composable
private fun AddTransactionDialog(
    categories: List<Category>,
    onDismiss: () -> Unit,
    onConfirm: (amount: Long, categoryId: Long, description: String?, type: TransactionType) -> Unit,
    onOpenAddCategory: () -> Unit
) {
    var amountText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(TransactionType.EXPENSE) }
    var selectedCategory by remember { mutableStateOf(categories.firstOrNull()) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Додати транзакцію",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Вибір типу транзакції (Дохід / Витрата)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedType == TransactionType.EXPENSE,
                        onClick = { selectedType = TransactionType.EXPENSE },
                        label = { Text("Витрата", style = MaterialTheme.typography.labelLarge) },
                        modifier = Modifier.weight(1f)
                    )

                    FilterChip(
                        selected = selectedType == TransactionType.INCOME,
                        onClick = { selectedType = TransactionType.INCOME },
                        label = { Text("Дохід", style = MaterialTheme.typography.labelLarge) },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Поле для вводу суми
                OutlinedTextField(
                    value = amountText,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            amountText = it
                            errorMessage = null
                        }
                    },
                    label = { Text("Сума (грн)", style = MaterialTheme.typography.bodyMedium) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Вибір категорії
                if (categories.isEmpty()) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Немає доступних категорій.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Button(
                                onClick = onOpenAddCategory,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Category,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Створити категорію", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = selectedCategory?.name ?: "Оберіть категорію",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Категорія", style = MaterialTheme.typography.bodyMedium) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { isDropdownExpanded = true },
                            trailingIcon = {
                                TextButton(onClick = { isDropdownExpanded = true }) {
                                    Text("Змінити", style = MaterialTheme.typography.labelMedium)
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category.name, style = MaterialTheme.typography.bodyLarge) },
                                    onClick = {
                                        selectedCategory = category
                                        isDropdownExpanded = false
                                        errorMessage = null
                                    }
                                )
                            }
                        }
                    }
                }

                // Поле для опису
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { descriptionText = it },
                    label = { Text("Опис (необов'язково)", style = MaterialTheme.typography.bodyMedium) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amount = amountText.toLongOrNull()
                    val category = selectedCategory

                    when {
                        amount == null || amount <= 0 -> {
                            errorMessage = "Будь ласка, введіть коректну суму"
                        }

                        category == null -> {
                            errorMessage = "Будь ласка, оберіть категорію"
                        }

                        else -> {
                            onConfirm(amount, category.id, descriptionText, selectedType)
                        }
                    }
                }
            ) {
                Text("Додати", style = MaterialTheme.typography.labelLarge)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати", style = MaterialTheme.typography.labelLarge)
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Main Screen - Light"
)
@Composable
private fun MainScreenLightPreview() {
    val sampleCategories = listOf(
        Category(id = 1, name = "Продукти", icon = null, isActive = true),
        Category(id = 2, name = "Зарплата", icon = null, isActive = true)
    )

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
            description = "Покупки в супермаркеті",
            date = System.currentTimeMillis() - 3600000,
            type = TransactionType.EXPENSE
        )
    )

    AppTheme(darkTheme = false) {
        MainScreenContent(
            uiState = MainUiState(
                transactions = sampleTransactions,
                categories = sampleCategories,
                totalIncome = 15000,
                totalExpense = 1700,
                threeLatestTransaction = sampleTransactions
            ),
            onNavigateToSettings = {},
            onNavigateToTransactionHistory = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Main Screen - Dark"
)
@Composable
private fun MainScreenDarkPreview() {
    val sampleCategories = listOf(
        Category(id = 1, name = "Продукти", icon = null, isActive = true),
        Category(id = 2, name = "Зарплата", icon = null, isActive = true)
    )

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
            description = "Покупки в супермаркеті",
            date = System.currentTimeMillis() - 3600000,
            type = TransactionType.EXPENSE
        )
    )

    AppTheme(darkTheme = true) {
        MainScreenContent(
            uiState = MainUiState(
                transactions = sampleTransactions,
                categories = sampleCategories,
                totalIncome = 15000,
                totalExpense = 1700,
                threeLatestTransaction = sampleTransactions
            ),
            onNavigateToSettings = {},
            onNavigateToTransactionHistory = {}
        )
    }
}

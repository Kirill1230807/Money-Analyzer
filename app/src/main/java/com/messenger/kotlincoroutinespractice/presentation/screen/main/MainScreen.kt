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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Money
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
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.backgroundDark
import com.messenger.kotlincoroutinespractice.domain.model.category.Category
import com.messenger.kotlincoroutinespractice.domain.model.transaction.Transaction
import com.messenger.kotlincoroutinespractice.domain.model.transaction.TransactionType
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTheme
import com.messenger.kotlincoroutinespractice.presentation.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        uiState = uiState,
        onAddTransactionClick = viewModel::showAddTransactionDialog,
        onDismissTransactionDialog = viewModel::dismissAddTransactionDialog,
        onAddCategoryClick = viewModel::showAddCategoryDialog,
        onDismissCategoryDialog = viewModel::dismissAddCategoryDialog,
        onCreateCategory = viewModel::addCategory,
        onCreateTransaction = viewModel::addTransaction
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
    onCreateTransaction: (amount: Long, categoryId: Long, description: String?, type: TransactionType) -> Unit = { _, _, _, _ -> }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Головний екран",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
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
            // Блок доходу та витрат

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
                        fontSize = 13.sp
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
                        fontSize = 13.sp
                    )
                }
            }

            // Заголовок списку транзакцій
            Text(
                text = "Історія транзакцій",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // Відображення транзакцій як список (LazyColumn)
            if (uiState.transactions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Транзакцій поки немає",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = uiState.transactions,
                        key = { it.id }
                    ) { transaction ->
                        val categoryName = uiState.categories
                            .find { it.id == transaction.categoryId }?.name
                            ?: "Категорія #${transaction.categoryId}"

                        TransactionItem(
                            transaction = transaction,
                            categoryName = categoryName
                        )
                    }
                }
            }
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
    val backgroundColor = Color(0xFF81C784)
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
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF81C784)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "₴",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Medium
                    )
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "$amount ₴",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
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
        if (isDark) Color(0xFF1E3A24) else Color(0xFFE8F5E9)
    } else {
        if (isDark) Color(0xFF3E1E1E) else Color(0xFFFFEBEE)
    }

    val contentColor = if (isIncome) {
        if (isDark) Color(0xFF81C784) else Color(0xFF2E7D32)
    } else {
        if (isDark) Color(0xFFE57373) else Color(0xFFC62828)
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
                    style = MaterialTheme.typography.bodyMedium,
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
private fun TransactionItem(
    transaction: Transaction,
    categoryName: String
) {
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

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
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

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )

                val description = transaction.description
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
                text = "${if (isIncome) "+" else "-"}${transaction.amount} ₴",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = amountColor
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
                    label = { Text("Назва категорії") },
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
                Text("Зберегти")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
                        label = { Text("Витрата") },
                        modifier = Modifier.weight(1f)
                    )

                    FilterChip(
                        selected = selectedType == TransactionType.INCOME,
                        onClick = { selectedType = TransactionType.INCOME },
                        label = { Text("Дохід") },
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
                    label = { Text("Сума (грн)") },
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
                                Text("Створити категорію")
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = selectedCategory?.name ?: "Оберіть категорію",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Категорія") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { isDropdownExpanded = true },
                            trailingIcon = {
                                TextButton(onClick = { isDropdownExpanded = true }) {
                                    Text("Змінити")
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
                                    text = { Text(category.name) },
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
                    label = { Text("Опис (необов'язково)") },
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
                Text("Додати")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Скасувати")
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Main Screen - Light"
)
@Composable
private fun MainScreenPreview() {
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
        )
    )

    AppTheme(darkTheme = false) {
        MainScreenContent(
            uiState = MainUiState(
                transactions = sampleTransactions,
                categories = sampleCategories,
                totalIncome = 15000,
                totalExpense = 850
            )
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
        )
    )

    AppTheme(darkTheme = true) {
        MainScreenContent(
            uiState = MainUiState(
                transactions = sampleTransactions,
                categories = sampleCategories,
                totalIncome = 15000,
                totalExpense = 850
            )
        )
    }
}

package com.messenger.kotlincoroutinespractice.presentation.navigation

/**
 * Описує всі екрани (маршрути) додатку для Навігації.
 */
sealed class Screen(val route: String) {

    /** Головний екран з оглядом балансу та останніх транзакцій */
    object Main : Screen("main_screen")

    /** Екран зі списком та управлінням категоріями */
    object Categories : Screen("categories_screen")

    /** Екран деталей конкретної транзакції з передачею аргументу ID */
    object TransactionHistory : Screen("transaction_history") {
    }

    /** Екран налаштувань додатку */
    object Settings : Screen("settings_screen")
}

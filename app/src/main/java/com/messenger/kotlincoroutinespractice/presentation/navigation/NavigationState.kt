package com.messenger.kotlincoroutinespractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Стейт-холдер для управління навігацією в додатку.
 * Інкапсулює роботу з [NavHostController].
 */
class NavigationState(
    val navController: NavHostController,
) {
    /**
     * Перехід на вказаний екран.
     */
    fun navigateTo(route: String) {
        navController.navigate(route) {
            // Зберігаємо стан при перенаправленні між екранами
            launchSingleTop = true
        }
    }

    /**
     * Перехід на екран [Screen].
     */
    fun navigateTo(screen: Screen) {
        navigateTo(screen.route)
    }

    /**
     * Повернення на попередній екран у стекі.
     */
    fun navigateBack() {
        navController.popBackStack()
    }

    /**
     * Очищення стеку та перехід на новий екран.
     */
    fun navigateAndClearBackStack(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}

/**
 * Створення та запам'ятовування об'єкта [NavigationState].
 */
@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController()
): NavigationState {
    return remember(navController) {
        NavigationState(navController)
    }
}

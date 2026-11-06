package com.messenger.kotlincoroutinespractice.presentation.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.messenger.kotlincoroutinespractice.presentation.screen.main.MainScreen
import com.messenger.kotlincoroutinespractice.presentation.screen.transactionsHistory.TransactionHistory

private const val TRANSITION_DURATION = 350

/**
 * Головний граф навігації додатку.
 * Поєднує [Screen] маршрути та відображає відповідні composable-екрани з плавними анімаціями переходу.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navigationState: NavigationState = rememberNavigationState(),
    startDestination: String = Screen.Main.route,
) {
    NavHost(
        navController = navigationState.navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),

        // Анімація при переході вперед (новий екран з'являється справа)
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                initialOffsetX = { fullWidth -> fullWidth }
            ) + fadeIn(
                animationSpec = tween(TRANSITION_DURATION, easing = LinearOutSlowInEasing)
            ) + scaleIn(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                initialScale = 0.96f
            )
        },
        // Анімація для поточного екрану при переході далі (зсув вліво з ефектом паралаксу)
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                targetOffsetX = { fullWidth -> - (fullWidth * 0.25f).toInt() }
            ) + fadeOut(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing)
            ) + scaleOut(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                targetScale = 0.96f
            )
        },
        // Анімація для попереднього екрану при поверненні назад (повернення зліва)
        popEnterTransition = {
            slideInHorizontally(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                initialOffsetX = { fullWidth -> - (fullWidth * 0.25f).toInt() }
            ) + fadeIn(
                animationSpec = tween(TRANSITION_DURATION, easing = LinearOutSlowInEasing)
            ) + scaleIn(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                initialScale = 0.96f
            )
        },
        // Анімація при поверненні назад (поточний екран виходить вправо)
        popExitTransition = {
            slideOutHorizontally(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                targetOffsetX = { fullWidth -> fullWidth }
            ) + fadeOut(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing)
            ) + scaleOut(
                animationSpec = tween(TRANSITION_DURATION, easing = FastOutSlowInEasing),
                targetScale = 0.96f
            )
        }
    ) {
        // Головний екран
        composable(route = Screen.Main.route) {
            MainScreen(
                onNavigateToTransactionHistory = {
                    navigationState.navigateTo(Screen.TransactionHistory)
                },
                onNavigateToSettings = {
                    navigationState.navigateTo(Screen.Settings)
                }
            )
        }

        // Екран налаштувань (каркас для розширення)
        composable(route = Screen.Settings.route) {
            PlaceholderScreen(
                title = "Налаштування",
                description = "Тут будуть налаштування додатку",
                onBackClick = { navigationState.navigateBack() }
            )
        }

        composable(route = Screen.TransactionHistory.route) {
            TransactionHistory(
                onBackClick = { navigationState.navigateBack() }
            )
        }
    }
}

/**
 * Тимчасовий компонент-заглушка для демонстрації навігації на майбутні екрани.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceholderScreen(
    title: String,
    description: String,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

package com.example.watchlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.watchlist.auth.presentation.view.LoginScreen
import com.example.watchlist.auth.presentation.view.RegisterScreen
import com.example.watchlist.home.HomeScreen

@Composable
fun WatchlistNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {

        // 🔐 LOGIN
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavRoutes.REGISTER)
                }
            )
        }

        // 🆕 REGISTER
        composable(NavRoutes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // 🏠 HOME
        composable(NavRoutes.HOME) {
            HomeScreen(
                onLogoutSuccess = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}
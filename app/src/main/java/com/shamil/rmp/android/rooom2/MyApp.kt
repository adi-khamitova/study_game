package com.example.game3

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("game") {
            GameScreen()
        }
        composable("results") {
            // Получите результаты из базы данных
            val results = emptyList<Results>() // Замените на фактический вызов
            ResultsScreen(results = results)
        }
        composable("profile") {
            // Получите имя пользователя
            val playerName = "" // Замените на фактический вызов
            ProfileScreen(playerName = playerName)
        }
    }
}

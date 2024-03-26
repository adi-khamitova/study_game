package com.example.game3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    // Ваш код для отображения главного экрана с тремя кнопками
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Пример кнопки "Старт"
        ClickableText(
            text = AnnotatedString("Старт"),
            onClick = { offset ->
                navController.navigate("game")
            },
            modifier = Modifier
                .padding(8.dp)
                .clickable { /* Дополнительные настройки для кнопки "Старт" */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Пример кнопки "Результаты"
        ClickableText(
            text = AnnotatedString("Результаты"),
            onClick = { offset ->
                navController.navigate("results")
            },
            modifier = Modifier
                .padding(8.dp)
                .clickable { /* Дополнительные настройки для кнопки "Результаты" */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Пример кнопки "Личный кабинет"
        ClickableText(
            text = AnnotatedString("Личный кабинет"),
            onClick = { offset ->
                navController.navigate("profile")
            },
            modifier = Modifier
                .padding(8.dp)
                .clickable { /* Дополнительные настройки для кнопки "Личный кабинет" */ }
        )
    }
}

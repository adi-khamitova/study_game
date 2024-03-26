package com.example.game3

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen() {
    val context = LocalContext.current
    var playerName by rememberSaveable { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }
    var lives by remember { mutableStateOf(3) }
    var currentQuestion by remember { mutableStateOf(0) }
    var showResultsDialog by remember { mutableStateOf(false) }

    // Генерация вопросов и ответов (замените на свою логику)
    val questions = listOf(
        Question("Вопрос 1", listOf("Ответ 1", "Ответ 2", "Ответ 3", "Ответ 4"), 2),
        Question("Вопрос 2", listOf("Ответ 1", "Ответ 2", "Ответ 3", "Ответ 4"), 3),
        // ... добавьте остальные вопросы
    )

    // Обработка ответа пользователя
    fun onAnswerSelected(answerIndex: Int) {
        if (questions[currentQuestion].correctAnswerIndex == answerIndex) {
            score++
        } else {
            lives--
        }

        // Переход к следующему вопросу или завершение игры
        if (currentQuestion < questions.size - 1) {
            currentQuestion++
        } else {
            showResultsDialog = true
            // Сохраните результат в базу данных
            saveResult(context, playerName, score)
        }
    }

    // Ваш код для отображения экрана игры
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Вопрос ${currentQuestion + 1}/${questions.size}")
        Text(text = "Жизни: $lives")
        Text(text = "Счет: $score")

        Spacer(modifier = Modifier.height(16.dp))

        // Вопрос
        Text(text = questions[currentQuestion].text, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Ответы
        questions[currentQuestion].options.forEachIndexed { index, option ->
            Button(
                onClick = { onAnswerSelected(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = option)
            }
        }
    }

    // Если нужно показать диалог с результатами
    if (showResultsDialog) {
        ResultDialog(
            playerName = playerName,
            score = score,
            onClose = { showResultsDialog = false }
        )
    }
}

data class Question(val text: String, val options: List<String>, val correctAnswerIndex: Int)

fun saveResult(context: Context, playerName: String, score: Int) {
    // Используйте Room для сохранения результатов в базе данных
    val resultDao = AppDatabase.getInstance(context).resultDao()

    val result = Results(playerName = playerName, score = score)
    resultDao.insertResult(result)
}

@Composable
fun ResultDialog(playerName: String, score: Int, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onClose() },
        title = {
            Text(text = "Результаты игры")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Игрок: $playerName")
                Text("Счет: $score")
            }
        },
        confirmButton = {
            Button(
                onClick = onClose,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("В начальное меню")
            }
        }
    )
}


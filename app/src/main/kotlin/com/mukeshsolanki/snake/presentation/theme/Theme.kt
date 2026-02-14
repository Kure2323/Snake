package com.mukeshsolanki.snake.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import com.mukeshsolanki.snake.R

private val colorScheme = darkColorScheme(
    primary = DarkGreen,
    secondary = DarkGreen,
    tertiary = DarkGreen,
    background = LightGreen,
    onPrimary = Color.White,
    onBackground = DarkGreen
)

val SnakeFontFamily = androidx.compose.ui.text.font.FontFamily(
    Font(R.font.snake) // Asegúrate de que el archivo se llame snake.ttf o snake.otf
)

@Composable
fun SnakeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package com.mukeshsolanki.snake.presentation.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mukeshsolanki.snake.data.model.State
import com.mukeshsolanki.snake.domain.game.GameEngine
import com.mukeshsolanki.snake.domain.game.TypeFood
import com.mukeshsolanki.snake.presentation.theme.DarkGreen
import com.mukeshsolanki.snake.presentation.theme.border2dp
import com.mukeshsolanki.snake.presentation.theme.corner4dp
import com.mukeshsolanki.snake.presentation.theme.padding16dp

@Composable
fun Board(
    state: State,
    typeFood: Int
    ) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 400,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorAnimation"
    )

    val speedColor by infiniteTransition.animateColor(
        initialValue = Color.Blue,
        targetValue = Color.Black,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "colorAnimation"
    )



    BoxWithConstraints(Modifier.padding(padding16dp)) {
        val tileSize = maxWidth / GameEngine.BOARD_SIZE
        Box(
            Modifier
                .size(maxWidth)
                .border(border2dp,
                    color = if (typeFood == TypeFood.BORRACHA.value)
                        animatedColor
                    else if (typeFood == TypeFood.VELOZ.value)
                        speedColor
                    else
                        DarkGreen
                )
        )
        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(
                    DarkGreen, CircleShape
                )
        )
        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(
                        DarkGreen, RoundedCornerShape(corner4dp)
                    )
            )
        }
    }
}
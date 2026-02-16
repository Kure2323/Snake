package com.mukeshsolanki.snake.presentation.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.data.model.State
import com.mukeshsolanki.snake.domain.game.GameEngine
import com.mukeshsolanki.snake.domain.game.TypeFood
import com.mukeshsolanki.snake.presentation.theme.DarkGreen
import com.mukeshsolanki.snake.presentation.theme.SnakeFontFamily
import com.mukeshsolanki.snake.presentation.theme.border2dp
import com.mukeshsolanki.snake.presentation.theme.corner4dp
import com.mukeshsolanki.snake.presentation.theme.padding16dp
import kotlinx.coroutines.delay

@Composable
fun Board(
    state: State,
    typeFood: Int
    ) {
    val infiniteTransition = rememberInfiniteTransition()

    var imageBorracho: Int by remember {
        mutableStateOf(R.drawable.raul)
    }

    var onBackgroundChange by remember { mutableStateOf(0) }

    LaunchedEffect(onBackgroundChange) {
        while (true){
            onBackgroundChange = (0..1).random()
            delay(2000)
        }
    }

    // Cambio de Serpiente
    LaunchedEffect(typeFood) {

        val borrachos = listOf(
            R.drawable.nacho,
            R.drawable.nacho2,
            R.drawable.raul,
            R.drawable.raul2,
            R.drawable.izan,
            R.drawable.izanypol,
            R.drawable.pol
        )

        while (typeFood == TypeFood.BORRACHA.value) {

            val index = (0..< borrachos.size).random()

            delay(2000L)
            imageBorracho = borrachos[index]

        }

    }

    val drunkColor by infiniteTransition.animateColor(
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


    Box(
        contentAlignment = Alignment.Center
    ){
        if (typeFood != TypeFood.NORMAL.value){
            Text(
                text = if (typeFood == TypeFood.BORRACHA.value){
                    "DRUNK!!"
                } else {
                    "SPEED!!"
                },
                color = if (typeFood == TypeFood.BORRACHA.value){
                    drunkColor
                } else {
                    speedColor
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = SnakeFontFamily,
                fontSize = 40.sp
            )
        }

        BoxWithConstraints(Modifier.padding(padding16dp)) {
            val tileSize = maxWidth / GameEngine.BOARD_SIZE

            Box(
                Modifier
                    .size(maxWidth)
                    .border(border2dp,
                        color = when (typeFood) {
                            TypeFood.BORRACHA.value -> drunkColor
                            TypeFood.VELOZ.value -> speedColor
                            else -> DarkGreen
                        }
                    )
            ) {
                when(typeFood){

                    TypeFood.BORRACHA.value -> {

                        Image(
                            painter = painterResource(
                                if (onBackgroundChange == 1) R.drawable.cerveza_g else R.drawable.cerveza2
                            ),
                            contentDescription = "Cerveza Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().alpha(0.5F)
                        )

                    }

                    TypeFood.VELOZ.value -> {
                        Image(
                            painter = painterResource(
                                if (onBackgroundChange == 1) R.drawable.sonic2 else R.drawable.sonic
                            ),
                            contentDescription = "Velocidad Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().alpha(0.5F)
                        )
                    }

                }

            }
            Box(
                Modifier
                    .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                    .size(tileSize)
                    .background(
                        DarkGreen, CircleShape
                    )
            )

            // Snake box
            state.snake.forEach {
                if (typeFood == TypeFood.BORRACHA.value) {
                    Box(
                        modifier = Modifier
                            .offset(x = tileSize * it.first, y = tileSize * it.second)
                            .size((tileSize.value + 10).dp)
                    ) {
                        Image(
                            painter = painterResource(imageBorracho),
                            contentDescription = "Imágenes de borrachos",
                            contentScale = ContentScale.Fit
                        )

                    }
                } else {
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
    }
}
package com.mukeshsolanki.snake.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.domain.game.GameEngine
import com.mukeshsolanki.snake.domain.game.SnakeDirection
import com.mukeshsolanki.snake.domain.game.TypeFood
import com.mukeshsolanki.snake.presentation.activity.GameActivity
import com.mukeshsolanki.snake.presentation.component.AppBar
import com.mukeshsolanki.snake.presentation.component.Board
import com.mukeshsolanki.snake.presentation.component.Controller
import com.mukeshsolanki.snake.presentation.theme.DarkGreen
import com.mukeshsolanki.snake.presentation.theme.SnakeFontFamily
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    gameEngine: GameEngine,
    score: Int,
    typeFood: Int
) {
    val state = gameEngine.state.collectAsState(initial = null)
    val activity = LocalContext.current as GameActivity

    /** CONTADOR PARA EMPEZAR LA PARTIDA **/
    var startCount by remember { mutableStateOf(5) }
    val isContActive = startCount > 0
    AppBar(
        title = stringResource(id = R.string.your_score, score),
        onBackClicked = { activity.finish() }) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LaunchedEffect(startCount) {
                    if (startCount > 0){
                        delay(1000L)
                        startCount--
                    }
                }


                state.value?.let { Board(it, typeFood) }
                Controller {
                    when(typeFood) {
                        TypeFood.BORRACHA.value -> {
                            when (it) {
                                SnakeDirection.Down -> gameEngine.move = Pair(0, -1)
                                SnakeDirection.Right -> gameEngine.move = Pair(-1, 0)
                                SnakeDirection.Left -> gameEngine.move = Pair(1, 0)
                                SnakeDirection.Up -> gameEngine.move = Pair(0, 1)
                            }
                        }
                        TypeFood.NORMAL.value -> {
                            when (it) {
                                SnakeDirection.Up -> gameEngine.move = Pair(0, -1)
                                SnakeDirection.Left -> gameEngine.move = Pair(-1, 0)
                                SnakeDirection.Right -> gameEngine.move = Pair(1, 0)
                                SnakeDirection.Down -> gameEngine.move = Pair(0, 1)
                            }
                        }
                        TypeFood.VELOZ.value -> {
                            when (it) {
                                SnakeDirection.Up -> gameEngine.move = Pair(0, -1)
                                SnakeDirection.Left -> gameEngine.move = Pair(-1, 0)
                                SnakeDirection.Right -> gameEngine.move = Pair(1, 0)
                                SnakeDirection.Down -> gameEngine.move = Pair(0, 1)
                            }
                        }
                    }
                }
            }

            if (isContActive){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = startCount.toString(),
                        fontFamily = SnakeFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = DarkGreen,
                        fontSize = 40.sp
                    )
                }
            }
        }

    }
}
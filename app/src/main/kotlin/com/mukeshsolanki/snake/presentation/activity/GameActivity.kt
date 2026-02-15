package com.mukeshsolanki.snake.presentation.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.data.Container
import com.mukeshsolanki.snake.data.cache.GameCache
import com.mukeshsolanki.snake.data.model.HighScore
import com.mukeshsolanki.snake.domain.base.BaseActivity
import com.mukeshsolanki.snake.domain.game.GameEngine
import com.mukeshsolanki.snake.domain.game.TypeFood
import com.mukeshsolanki.snake.presentation.screen.EndScreen
import com.mukeshsolanki.snake.presentation.screen.GameScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GameActivity : BaseActivity() {
    private lateinit var dataStore: GameCache
    private val isPlaying = mutableStateOf(true)
    private var score = mutableStateOf(0)
    private lateinit var scope: CoroutineScope
    private lateinit var playerName: String
    private lateinit var highScores: List<HighScore>

    private var typeFood = mutableStateOf(TypeFood.NORMAL.value)
    private var gameEngine = GameEngine(
        scope = lifecycleScope,
        onGameEnded = {
            if (isPlaying.value) {
                isPlaying.value = false
                scope.launch { dataStore.saveHighScore(highScores) }
            }
        },
        onFoodEaten = {
            score.value++
            typeFood.value = (0..2).random()
                      },
        typeFood = typeFood
    )

    @Composable
    override fun Content() {

        // Coroutine for setting the timer
        LaunchedEffect(Unit) {
            Container.startTimer()
        }


        scope = rememberCoroutineScope()
        dataStore = GameCache(applicationContext)
        playerName =
            dataStore.getPlayerName.collectAsState(initial = stringResource(id = R.string.default_player_name)).value
        highScores = dataStore.getHighScores.collectAsState(initial = listOf()).value.plus(
            HighScore(playerName, score.value)
        )//.sortedByDescending { it.score }.take(TOP_10)
        Column {
            if (isPlaying.value) {
                GameScreen(gameEngine, score.value, typeFood.value)
            } else {
                EndScreen(score.value) {
                    score.value = 0
                    gameEngine.reset()
                    isPlaying.value = true
                }
                typeFood.value = TypeFood.NORMAL.value
            }
        }
    }
}
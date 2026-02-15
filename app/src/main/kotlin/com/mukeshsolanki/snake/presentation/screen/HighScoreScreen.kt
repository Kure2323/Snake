package com.mukeshsolanki.snake.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.data.cache.GameCache
import com.mukeshsolanki.snake.data.model.GameEntity
import com.mukeshsolanki.snake.data.model.HighScore
import com.mukeshsolanki.snake.domain.base.TOP_10
import com.mukeshsolanki.snake.presentation.component.AppBar
import com.mukeshsolanki.snake.presentation.component.TitleLarge
import com.mukeshsolanki.snake.presentation.theme.DarkGreen
import com.mukeshsolanki.snake.presentation.theme.SnakeFontFamily
import com.mukeshsolanki.snake.presentation.theme.padding16dp
import com.mukeshsolanki.snake.presentation.theme.padding8dp
import com.mukeshsolanki.snake.presentation.viewmodel.HighScoreViewModel

@Composable
fun HighScoreScreen(
    navController: NavHostController,
    viewModel: HighScoreViewModel = viewModel()
) {
    val dataStore = GameCache(LocalContext.current)
    val highScores =
        dataStore.getHighScores.collectAsState(initial = listOf()).value.sortedByDescending { it.score }
            .take(TOP_10)

    val dbScores by viewModel.uiState.collectAsState()


    AppBar(
        title = stringResource(R.string.high_score),
        onBackClicked = { navController.popBackStack() }) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                ),

        ){
            items(dbScores, key = { it!!.id }){ score ->
                NewHighScoreItem(
                    rank = dbScores.indexOf(score)+1,
                    score = score
                )
            }
        }
    }
}

@Composable
private fun HighScoreItem(highScore: HighScore) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(padding8dp)
    ) {
        TitleLarge(
            text = highScore.playerName,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        TitleLarge(
            text = highScore.score.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NewHighScoreItem(
    rank: Int,
    score: GameEntity?
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black, RoundedCornerShape(12.dp))
            .padding(bottom = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGreen, RoundedCornerShape(10.dp))
                .border(
                    width = 2.dp,
                    color = Color.White.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Black, RoundedCornerShape(4.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "#$rank",
                            color = Color.Yellow,
                            fontFamily = SnakeFontFamily,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "${score?.player}",
                        color = Color.White,
                        fontFamily = SnakeFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "${score?.points} PTS",
                    color = Color.LightGray,
                    fontFamily = SnakeFontFamily,
                    fontSize = 20.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .height(2.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "TIME: ${score?.secondsAlive}S",
                    color = Color.White.copy(alpha = 0.7f),
                    fontFamily = SnakeFontFamily,
                    fontSize = 14.sp
                )
            }
        }
    }
}
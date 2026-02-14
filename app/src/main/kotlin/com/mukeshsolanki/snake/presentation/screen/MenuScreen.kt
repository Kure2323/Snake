package com.mukeshsolanki.snake.presentation.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.domain.extension.launchActivity
import com.mukeshsolanki.snake.domain.navigation.Screen
import com.mukeshsolanki.snake.presentation.activity.GameActivity
import com.mukeshsolanki.snake.presentation.component.DisplayLarge
import com.mukeshsolanki.snake.presentation.theme.border2dp
import com.mukeshsolanki.snake.presentation.theme.padding16dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import com.mukeshsolanki.snake.presentation.component.PixelButton

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding16dp)
            .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        val context = LocalContext.current
        DisplayLarge(text = stringResource(id = R.string.app_name))


        PixelButton(
            modifier = Modifier.width(248.dp),
            text = stringResource(R.string.new_game),
            onClick = { context.launchActivity<GameActivity>() }
        )

        PixelButton(
            modifier = Modifier.width(248.dp),
            text = stringResource(R.string.high_score),
            onClick = { navController.navigate(Screen.HighScores.route) }
        )

        PixelButton(
            modifier = Modifier.width(248.dp),
            text = stringResource(R.string.settings),
            onClick = { navController.navigate(Screen.Settings.route) }
        )

        PixelButton(
            modifier = Modifier.width(248.dp),
            text = stringResource(R.string.about),
            onClick = { navController.navigate(Screen.About.route) }
        )

    }
}
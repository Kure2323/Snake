package com.mukeshsolanki.snake.data.db

import android.app.Application
import com.mukeshsolanki.snake.data.repository.GameRepository
import kotlin.getValue

class GameApplication : Application() {

    private val database by lazy { GameDatabase.getDatabase(this) }

    val repository by lazy { GameRepository(database.gameDao()) }
}
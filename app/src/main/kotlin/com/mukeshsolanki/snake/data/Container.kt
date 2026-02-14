package com.mukeshsolanki.snake.data

import android.content.Context
import com.mukeshsolanki.snake.data.db.GameDatabase
import com.mukeshsolanki.snake.data.repository.GameRepository

object Container {

    lateinit var repository: GameRepository

    fun init(c: Context) {
        val db = GameDatabase.getDatabase(c)
        repository = GameRepository(db.gameDao())
    }

}
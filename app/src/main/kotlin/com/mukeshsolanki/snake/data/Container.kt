package com.mukeshsolanki.snake.data

import android.content.Context
import com.mukeshsolanki.snake.data.db.GameDatabase
import com.mukeshsolanki.snake.data.repository.GameRepository

object Container {

    lateinit var repository: GameRepository
    var millis: Long = 0L

    fun startTimer() {
        millis = System.currentTimeMillis()
    }

    fun stopTimer(): Long {
        val before = millis
        millis = 0L
        return ((System.currentTimeMillis() - before)/1000) - 5
    }

    fun init(c: Context) {
        val db = GameDatabase.getDatabase(c)
        repository = GameRepository(db.gameDao())
    }

}
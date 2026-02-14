package com.mukeshsolanki.snake.data.repository

import com.mukeshsolanki.snake.data.dao.GameDao
import com.mukeshsolanki.snake.data.model.GameEntity
import kotlinx.coroutines.flow.Flow

class GameRepository(private val dao: GameDao) {

    suspend fun getAllScores(): Flow<List<GameEntity>> {
        return dao.getAllScores()
    }

    suspend fun insertNewScore(score: GameEntity){
        dao.insertNewScore(score)
    }
}
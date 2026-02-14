package com.mukeshsolanki.snake.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "scores")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val player: String,
    val points: Int,
    val secondsAlive: Long
)

package com.mukeshsolanki.snake.data.model

data class State(
    val food: Pair<Int, Int>,
    val typeFood: Int,
    val snake: List<Pair<Int, Int>>,
    val currentDirection: Int
)
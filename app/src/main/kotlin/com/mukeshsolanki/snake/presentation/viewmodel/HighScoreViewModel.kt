package com.mukeshsolanki.snake.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukeshsolanki.snake.data.Container
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn

class HighScoreViewModel: ViewModel() {

    private val repo = Container.repository

    val uiState = repo.getAllScores().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        emptyList()
    )

}
package com.mukeshsolanki.snake.data.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mukeshsolanki.snake.R
import com.mukeshsolanki.snake.data.Container
import com.mukeshsolanki.snake.data.model.GameEntity
import com.mukeshsolanki.snake.data.model.HighScore
import com.mukeshsolanki.snake.domain.base.DATASTORE_KEY_HIGH_SCORES
import com.mukeshsolanki.snake.domain.base.DATASTORE_KEY_PLAYER_NAME
import com.mukeshsolanki.snake.domain.base.DATASTORE_NAME
import com.mukeshsolanki.snake.domain.base.TOP_10
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameCache(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)
        val HIGH_SCORES_KEY = stringPreferencesKey(DATASTORE_KEY_HIGH_SCORES)
        val PLAYER_NAME_KEY = stringPreferencesKey(DATASTORE_KEY_PLAYER_NAME)
    }

    val getHighScores: Flow<List<HighScore>> = context.dataStore.data.map { preferences ->
        val scores = preferences[HIGH_SCORES_KEY]
        val listType = object : TypeToken<List<HighScore?>?>() {}.type
        Gson().fromJson<List<HighScore>>(scores, listType) ?: listOf()
    }

    val getPlayerName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PLAYER_NAME_KEY] ?: context.getString(R.string.default_player_name)
    }

    suspend fun saveHighScore(highScores: List<HighScore>) {

        val filteredHighScores = highScores.sortedByDescending { it.score }.take(TOP_10)
        context.dataStore.edit { preferences ->
            preferences[HIGH_SCORES_KEY] = Gson().toJson(filteredHighScores)
        }

        // Obtener e insertar el highScore recién hecho
        val lastHighScore = highScores.last()
        val gameEntity = GameEntity(
            player = lastHighScore.playerName,
            points = lastHighScore.score,
            secondsAlive = Container.stopTimer()
        )

        Container.repository.insertNewScore(gameEntity)

    }

    suspend fun savePlayerName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[PLAYER_NAME_KEY] = name
        }
    }
}
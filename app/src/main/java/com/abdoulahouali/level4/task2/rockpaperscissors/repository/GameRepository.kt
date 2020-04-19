package com.abdoulahouali.level4.task2.rockpaperscissors.repository

import android.content.Context
import com.abdoulahouali.level4.task2.rockpaperscissors.dao.GameDao
import com.abdoulahouali.level4.task2.rockpaperscissors.database.GameDataBase
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameDataBase = GameDataBase.getDatabase(context)
        gameDao = gameDataBase.gameDao()
    }

    fun getAllGames(): List<Game> {
        return gameDao.getGamesHistory()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insert(game)
    }
}
package com.abdoulahouali.level4.task2.rockpaperscissors.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insert(game: Game)

    @Query("SELECT * from game_table ORDER BY date_played ASC")
    fun getGamesHistory(): List<Game>

    @Query("DELETE FROM game_table")
    suspend fun deleteAll()
}
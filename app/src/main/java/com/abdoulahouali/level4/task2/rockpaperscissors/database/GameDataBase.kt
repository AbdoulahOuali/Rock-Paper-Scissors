package com.abdoulahouali.level4.task2.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdoulahouali.level4.task2.rockpaperscissors.converters.Converters
import com.abdoulahouali.level4.task2.rockpaperscissors.dao.GameDao
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDataBase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {

        private const val DATABASE_NAME = "game_database"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GameDataBase? = null

        fun getDatabase(context: Context): GameDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDataBase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.abdoulahouali.level4.task2.rockpaperscissors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game_table")
data class Game(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_id")
    var gameId: Int?,

    @ColumnInfo(name = "date_played")
    var gameDate: Date,

    @ColumnInfo(name = "player_move")
    var playerMove: Int,

    @ColumnInfo(name = "computer_move")
    var computerMove: Int,

    var winner: String
)
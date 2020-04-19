package com.abdoulahouali.level4.task2.rockpaperscissors.model

import java.util.*

data class Game(
    var gameId: Int,
    var gamedate: Date,
    var playerMove: Int,
    var computerMove: Int,
    var result: Gameresult
)
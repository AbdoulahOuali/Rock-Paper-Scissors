package com.abdoulahouali.level4.task2.rockpaperscissors.converters

import androidx.room.TypeConverter
import com.abdoulahouali.level4.task2.rockpaperscissors.model.GameResult
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toResult(value: String) = enumValueOf<GameResult>(value)

    @TypeConverter
    fun fromResult(result: GameResult) = result.value
}
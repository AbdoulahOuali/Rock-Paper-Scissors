package com.abdoulahouali.level4.task2.rockpaperscissors.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.abdoulahouali.level4.task2.rockpaperscissors.R

class GameHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("GameHistoryActivity", "Inside GameHistoryActivity onCreate")
        setContentView(R.layout.activity_game_history)
        super.onCreate(savedInstanceState)
    }
}
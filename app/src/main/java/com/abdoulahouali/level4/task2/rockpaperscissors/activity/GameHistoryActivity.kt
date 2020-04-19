package com.abdoulahouali.level4.task2.rockpaperscissors.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.abdoulahouali.level4.task2.rockpaperscissors.R

class GameHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("GameHistoryActivity", "Inside GameHistoryActivity onCreate")
        setContentView(R.layout.activity_game_history)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        var clearHistory = menu.findItem(R.id.clear_history)
        clearHistory.isVisible = true

        var fetchHistory: MenuItem = menu.findItem(R.id.fetch_history)
        fetchHistory.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.clear_history -> {
                Log.i("MainActivity", "Clear History option clicked")

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
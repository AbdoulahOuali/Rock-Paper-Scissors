package com.abdoulahouali.level4.task2.rockpaperscissors.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.abdoulahouali.level4.task2.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity", "Inside MainActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setTitle(R.string.app_name)



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        var clearHistory = menu.findItem(R.id.clear_history)
        clearHistory.isVisible = false

        var fetchHistory: MenuItem = menu.findItem(R.id.fetch_history)
        fetchHistory.isVisible = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.fetch_history -> {
                Log.i("MainActivity", "View History option clicked")
                val intent = Intent(this, GameHistoryActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

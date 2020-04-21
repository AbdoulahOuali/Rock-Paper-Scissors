package com.abdoulahouali.level4.task2.rockpaperscissors.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdoulahouali.level4.task2.rockpaperscissors.R
import com.abdoulahouali.level4.task2.rockpaperscissors.adapter.GameAdapter
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game
import com.abdoulahouali.level4.task2.rockpaperscissors.repository.GameRepository
import kotlinx.android.synthetic.main.activity_game_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameHistoryActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val gameHistory = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(gameHistory)

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("GameHistoryActivity", "Inside GameHistoryActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        gamesPlayedRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        gamesPlayedRv.adapter = gameAdapter
        gamesPlayedRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        refreshHistory()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        var clearHistory = menu.findItem(R.id.clear_history)
        clearHistory.isVisible = true
        clearHistory.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        var fetchHistory: MenuItem = menu.findItem(R.id.fetch_history)
        fetchHistory.isVisible = false
        fetchHistory.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        R.id.clear_history -> {
            Log.i("MainActivity", "Clear History option clicked")
            CoroutineScope(Dispatchers.Main).launch {
                gameRepository.deleteAllGames()
            }
            Toast.makeText(
                this@GameHistoryActivity, "Game history has been cleared.",
                Toast.LENGTH_LONG
            ).show()
            refreshHistory()
            true
        }
        android.R.id.home -> {
            this.finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun refreshHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            val gameHistory = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@GameHistoryActivity.gameHistory.clear()
            this@GameHistoryActivity.gameHistory.addAll(gameHistory)
            this@GameHistoryActivity.gameAdapter.notifyDataSetChanged()
        }
    }
}
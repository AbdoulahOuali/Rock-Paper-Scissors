package com.abdoulahouali.level4.task2.rockpaperscissors.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abdoulahouali.level4.task2.rockpaperscissors.R
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game
import com.abdoulahouali.level4.task2.rockpaperscissors.repository.GameRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MainActivity", "Inside MainActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setTitle(R.string.app_name)
        gameRepository = GameRepository(this)

        initViews()
    }

    private fun initViews() {
        result_tv.text = ""

        getStatistics()

        rockBtn.setOnClickListener {
            player_move.setImageResource(R.drawable.rock)
            play(R.drawable.rock)
        }

        paperBtn.setOnClickListener {
            player_move.setImageResource(R.drawable.paper)
            play(R.drawable.paper)
        }

        scissorsBtn.setOnClickListener {
            player_move.setImageResource(R.drawable.scissors)
            play(R.drawable.scissors)
        }
    }


    private fun computerPlay(): Int {
        when ((0..2).random()) {
            0 -> {
                computer_move.setImageResource(R.drawable.rock)
                return R.drawable.rock
            }
            1 -> {
                computer_move.setImageResource(R.drawable.scissors)
                return R.drawable.scissors
            }
            2 -> {
                computer_move.setImageResource(R.drawable.paper)
                return R.drawable.paper
            }
        }

        return -1
    }

    private fun play(resourceId: Int) {
        when (resourceId) {
            R.drawable.rock -> {
                when (computerPlay()) {
                    R.drawable.rock -> {
                        result_tv.text = getString(R.string.draw)
                        saveGame(R.drawable.rock, resourceId, "Draw")
                    }
                    R.drawable.paper -> {
                        result_tv.text = getString(R.string.computer_wins)
                        saveGame(R.drawable.paper, resourceId, "Computer")
                    }
                    else -> {
                        result_tv.text = getString(R.string.player_wins)
                        saveGame(R.drawable.scissors, resourceId, "You")
                    }
                }
            }
            R.drawable.scissors -> {
                when (computerPlay()) {
                    R.drawable.rock -> {
                        result_tv.text = getString(R.string.computer_wins)
                        saveGame(R.drawable.rock, resourceId, "Computer")
                    }
                    R.drawable.paper -> {
                        result_tv.text = getString(R.string.player_wins)
                        saveGame(R.drawable.paper, resourceId, "You")
                    }
                    else -> {
                        result_tv.text = getString(R.string.draw)
                        saveGame(R.drawable.scissors, resourceId, "Draw")
                    }
                }
            }
            R.drawable.paper -> {
                when (computerPlay()) {
                    R.drawable.rock -> {
                        result_tv.text = getString(R.string.player_wins)
                        saveGame(R.drawable.rock, resourceId, "You")
                    }
                    R.drawable.paper -> {
                        result_tv.text = getString(R.string.draw)
                        saveGame(R.drawable.paper, resourceId, "Draw")
                    }
                    else -> {
                        result_tv.text = getString(R.string.computer_wins)
                        saveGame(R.drawable.scissors, resourceId, "Computer")
                    }
                }
            }
            else -> {
                Toast.makeText(
                    this, "Something went wrong while playing the game, start over!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateStatistics(win: Int, draw: Int, lose: Int) {
        player_stats_tv.text = "Win: $win Draw: " +
                "$draw Lose: $lose"
    }

    private fun saveGame(playerMove: Int, computerMove: Int, winner: String) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(
                    Game(
                        null,
                        Date.from(Instant.now()),
                        playerMove,
                        computerMove,
                        winner
                    )
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        var clearHistory = menu.findItem(R.id.clear_history)
        clearHistory.isVisible = false
        clearHistory.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)

        var fetchHistory: MenuItem = menu.findItem(R.id.fetch_history)
        fetchHistory.isVisible = true
        clearHistory.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
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

    private fun getStatistics() {

        var you = 0
        var draw = 0
        var computer = 0

        mainScope.launch {
            you = gameRepository.getGamesByWinner("You")
            draw = gameRepository.getGamesByWinner("Draw")
            computer = gameRepository.getGamesByWinner("Computer")
        }.invokeOnCompletion {
            updateStatistics(you, draw, computer)
        }
    }
}

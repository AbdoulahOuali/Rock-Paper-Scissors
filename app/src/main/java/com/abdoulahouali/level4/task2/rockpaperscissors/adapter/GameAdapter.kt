package com.abdoulahouali.level4.task2.rockpaperscissors.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdoulahouali.level4.task2.rockpaperscissors.R
import com.abdoulahouali.level4.task2.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.game_history_item.view.*

class GameAdapter(private val games: ArrayList<Game>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private lateinit var context: Context

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.game_date_tv.text = game.gameDate.toString()

            itemView.computer_move.setImageResource(game.computerMove)

            when (game.winner) {
                "Draw" -> itemView.result_tv.text = game.winner
                "Computer" -> itemView.result_tv.text = game.winner + "wins!"
                "You" -> itemView.result_tv.text = game.winner + "win!"
            }

            itemView.player_move.setImageResource(game.playerMove)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        context = parent.context
        return GameViewHolder(
            LayoutInflater.from(context).inflate(R.layout.game_history_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

}
package com.vincent.tictactoe.core.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vincent.tictactoe.core.Player;

/**
 * A subclass of GameStatus. Over means that the game is over and a player
 * has won
 */
public class Over extends GameStatus {
    Player winner;

    public Over(Player player) {
        this.winner = player;
    }

    @Override
    @JsonProperty
    String getMessage() {
        return winner.getName() + " has won the game";
    }
}

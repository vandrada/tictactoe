package com.vincent.tictactoe.core.status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A subclass of GameStatus. Over means that the game is over and a player
 * has won
 */
public class GameWin extends GameStatus {

    @Override
    @JsonProperty
    public String getMessage() {
        return "The game is over";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof GameWin);
    }
}

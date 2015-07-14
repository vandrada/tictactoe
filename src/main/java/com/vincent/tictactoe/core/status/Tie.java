package com.vincent.tictactoe.core.status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A subclass of GameStatus. Tie means that the game is over but no player
 * has won
 */
public class Tie extends GameStatus {

    @Override
    @JsonProperty
    public String getMessage() {
        return "The game is a tie";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof Tie);
    }
}

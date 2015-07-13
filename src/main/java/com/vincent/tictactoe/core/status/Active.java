package com.vincent.tictactoe.core.status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A subclass of GameStatus. Active means that the game has two players and
 * is not joinable.
 */
public class Active extends GameStatus {
    @Override
    @JsonProperty
    public String getMessage() {
        return "Game is currently active";
    }
}

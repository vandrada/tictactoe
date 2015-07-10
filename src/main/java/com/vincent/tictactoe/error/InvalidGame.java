package com.vincent.tictactoe.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This error is thrown when a player tries to make a move in a game that
 * they haven't joined yet.
 */
public class InvalidGame {
    String message;

    public InvalidGame(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getMessage() {
        return this.message;
    }

}

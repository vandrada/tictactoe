package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A game of tic-tac-toe
 * {
 *     "game": _id
 *     "players": {
 *         "player1": _
 *         "player2": _
 *     }
 * }
 */
public class Game {
    private long id;
    private Player player1;
    private Player player2;
    private GameBoard board;

    public Game() {
        // Jackson deserialization
    }

    public Game(long id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }

    @JsonProperty
    public Player getFirstPlayer() {
        return this.player1;
    }

    @JsonProperty
    public Player getSecondPlayer() {
        return this.player2;
    }

    @JsonProperty
    public boolean joinable() {
        return this.player1 == null || this.player2 == null;
    }
}

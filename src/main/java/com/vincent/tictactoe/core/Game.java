package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        this.board = new GameBoard();
    }

    public void update(Position pos, char mark) {
        this.board.mark(pos, mark);
    }

    public Player getPlayerByToken(String token) {
        if (token.equals(player1.getToken())) {
            return player1;
        }
        if (token.equals(player2.getToken())) {
            return player2;
        }

        return null;
    }

    public boolean validToken(String token) {
        return getPlayerByToken(token) != null;

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

    @JsonIgnore
    public GameBoard getBoard() {
        return this.board;
    }
}

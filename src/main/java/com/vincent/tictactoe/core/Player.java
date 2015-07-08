package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A player in a tic-tac-toe game
 * {
 *     "name": _
 *     "active game": _
 * }
 */
public class Player {
    // a players screen-name
    private String name;
    // a players active game
    private Game activeGame;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.activeGame = new Game();
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    @JsonProperty
    public Game getActiveGame() {
        return this.activeGame;
    }
}

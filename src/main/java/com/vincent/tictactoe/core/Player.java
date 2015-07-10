package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    // the mark that a player places on the board
    private String mark;
    // the players token that is used for validation
    private String token;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @JsonIgnore
    // the token should only be shown when a game is created or when a game
    // is joined
    public String getToken() {
        return this.token;
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    @JsonProperty
    public String getMark() {
        return this.mark;
    }
}

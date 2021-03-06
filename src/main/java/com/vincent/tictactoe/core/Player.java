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
    private String name;
    private Game activeGame;
    private String mark;
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

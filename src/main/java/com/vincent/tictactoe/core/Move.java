package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent a move in tic-tac-toe.
 * {
 *     "game": _
 *     "player": _
 * }
 */
public class Move {
    private Game game;
    private Player player;
    private Position pos;

    public Move() {
        // Jackson deserialization
    }

    public Move(Game game, Player player, Position pos) {
        this.game = game;
        this.player = player;
        this.pos = pos;
    }

    @JsonProperty
    public Game getGame() {
        return this.game;
    }

    @JsonProperty
    public Player getPlayer() {
        return this.player;
    }

    @JsonProperty
    public Position getPos() {
        return this.pos;
    }
}

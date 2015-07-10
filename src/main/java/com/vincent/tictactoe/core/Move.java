package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent a move in tic-tac-toe. It's JSON representation will be
 * {
 *     "game": _
 *     "player": _
 *     "command": "move"
 *     "xPos": _
 *     "yPos": _
 * }
 */
public class Move {
    private Game game;
    private Player player;
    private GameBoard.Positions pos;

    public Move() {
        // Jackson deserialization
    }

    public Move(Game game, Player player, GameBoard.Positions pos) {
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
    public GameBoard.Positions getPos() {
        return this.pos;
    }
}

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
    private int xPos;
    private int yPos;

    public Move() {
        // Jackson deserialization
    }

    public Move(Game game, Player player, int xPos, int yPos) {
        this.game = game;
        this.player = player;
        this.xPos = xPos;
        this.yPos = yPos;
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
    public int getXPos() {
        return this.xPos;
    }

    @JsonProperty
    public int getYPos() {
        return this.yPos;
    }
}

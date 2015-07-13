package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent a move in tic-tac-toe. The JSON representation is as
 * follows:
 * {
 *     "player": _
 *     "pos":    _
 *     "valid:   _
 * }
 * - `player` is the Player who made the move
 * - `pos`: is the position that was filled converted to the Position enum, i.e
 *   `BOTTOM_RIGHT`
 * - `valid` is whether or not the move was valid.
 *
 * Actual marking of the board is done in the constructor since a Move is
 * both immutable and ephemeral.
 */
public class Move {
    private Game game;
    private Player player;
    private Position pos;
    private boolean valid;

    public Move() {
        // Jackson deserialization
    }

    public Move(Game game, Player player, Position pos) {
        this.game = game;
        this.player = player;
        this.pos = pos;

        // Execute the actual move on the game board
        if (game.isCurrentPlayer(player) && this.game.positionEmpty(pos)) {
            this.valid = true;
            this.game.update(pos, player.getMark());
        } else {
            this.valid = false;
        }
    }

    @JsonIgnore
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

    @JsonProperty
    public boolean getValid() {
        return this.valid;
    }
}

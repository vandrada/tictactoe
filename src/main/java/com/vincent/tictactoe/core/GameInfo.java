package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class to show more information about a Game. In addition to the usual
 * fields in the Game, it also shows the board.
 */
public class GameInfo {
    Game game;

    public GameInfo(Game game) {
        this.game = game;
    }

    @JsonProperty
    public Game getGame() {
        return this.game;
    }

    @JsonProperty
    public GameBoard getGameBoard() {
        return this.game.getBoard();
    }
}

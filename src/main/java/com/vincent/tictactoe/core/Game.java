package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.vincent.tictactoe.core.status.*;

/**
 * A game of tic-tac-toe
 * {
 *     "game": _id
 *     "players": {
 *         "playerOne": _
 *         "playerTwo": _
 *     }
 * }
 */
public class Game {
    private long id;
    private Player currentPlayer;   // the player whose turn it is
    private Player playerOne;
    private Player playerTwo;
    private GameBoard board;
    private GameStatus status;

    public Game() {
        // Jackson deserialization
    }

    public Game(long id, Player playerOne, Player playerTwo) {
        this.id = id;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;   // TODO randomize?
        this.board = new GameBoard();
        determineGameStatus();
    }

    public void updateBoard(Position pos, String mark) {
        // Don't let players set a mark before someone has joined
        if (!this.joinable()) {
            this.board.mark(pos, mark);
        }
        currentPlayer = otherPlayer();
        this.determineGameStatus();
    }

    public Player getPlayerByToken(String token) {
        if (token.equals(playerOne.getToken())) {
            return playerOne;
        }
        if (token.equals(playerTwo.getToken())) {
            return playerTwo;
        }

        return null;
    }

    public boolean validToken(String token) {
        return getPlayerByToken(token) != null;
    }

    public boolean positionEmpty(Position pos) {
        return this.board.isEmpty(pos);
    }

    public boolean isCurrentPlayer(Player player) {
        return player.getToken().equals(this.currentPlayer.getToken());
    }

    @JsonProperty
    public long getId() {
        return this.id;
    }

    @JsonProperty
    public Player getFirstPlayer() {
        return this.playerOne;
    }

    @JsonProperty
    public Player getSecondPlayer() {
        return this.playerTwo;
    }

    @JsonIgnore
    public boolean joinable() {
        return this.playerOne == null || this.playerTwo == null;
    }

    @JsonProperty
    public GameStatus getGameStatus() {
        return this.status;
    }

    @JsonProperty
    public void determineGameStatus() {
        if (this.joinable()) {
            this.status = new Available();
        } else if (this.board.full()) {
            this.status = new Tie();
        } else if (this.board.check()) {
            // TODO determine winner
            // this.status = new Over(board.getWinner());
            this.status = new GameWin();
        } else {
            this.status = new Active();
        }
    }

    @JsonProperty
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @JsonIgnore
    public GameBoard getBoard() {
        return this.board;
    }

    @JsonIgnore
    public Player otherPlayer() {
        if (this.currentPlayer == this.getFirstPlayer()) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }
}

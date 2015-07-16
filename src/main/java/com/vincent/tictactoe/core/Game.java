package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.vincent.tictactoe.core.status.*;

/**
 * A game of tic-tac-toe
 * {
 *     "game": _
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
        this.currentPlayer = playerOne;
        this.board = new GameBoard();
        determineGameStatus();
    }

    /**
     * Updates the board
     * @param pos the position to place the mark
     * @param mark the mark to place at {@code pos}
     */
    public void updateBoard(Position pos, String mark) {
        // Don't let players set a mark before someone has joined
        if (this.isActive()) {
            this.board.mark(pos, mark);
            currentPlayer = otherPlayer();
            determineGameStatus();
        }
    }

    /**
     * Retrieves either Player One or Player 2 based on their token
     * @param token the token to match against this Game's players
     * @return a player with token {@code token} or null wrapped in an Option
     */
    public Optional<Player> getPlayerByToken(String token) {
        if (token.equals(playerOne.getToken())) {
            return Optional.of(playerOne);
        }
        if (token.equals(playerTwo.getToken())) {
            return Optional.of(playerTwo);
        }

        return Optional.absent();
    }

    /**
     * Checks if a token is valid for this Game, i.e it matches a player that
     * is attached to this Game
     * @param token the token to check
     * @return true if the token is associated with a Player; or false
     */
    public boolean validToken(String token) {
        return getPlayerByToken(token).isPresent();
    }

    /**
     * Checks if a position in empty
     * @param pos the position to check
     * @return true if the position is empty; false otherwise
     */
    public boolean positionEmpty(Position pos) {
        return this.board.isEmpty(pos);
    }

    /**
     * Checks if a Player is the current Player
     * @param player the Player to check
     * @return true if the Player is the current Player; false otherwise
     */
    public boolean isCurrentPlayer(Player player) {
        return player.getToken().equals(this.currentPlayer.getToken());
    }

    /**
     * @return true if the Game is active; false otherwise
     */
    @JsonIgnore
    public boolean isActive() {
        return this.status.equals(new Active());
    }

    /**
     * Alternates between the Players in this game
     * @return the "next" Player
     */
    @JsonIgnore
    public Player otherPlayer() {
        if (this.currentPlayer == this.getFirstPlayer()) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    /**
     * @return true if the Game only has one Player; false otherwise
     */
    @JsonIgnore
    public boolean joinable() {
        return this.playerOne == null || this.playerTwo == null;
    }

    /**
     * Determines the status of the Game. The status could be any of the
     * subclasses of {@code GameStatus}
     */
    @JsonProperty
    public void determineGameStatus() {
        if (this.joinable()) {
            this.status = new Available();
        } else if (this.board.full() && !this.board.check()) {
            this.status = new Tie();
        } else if (this.board.check()) {
            this.status = new GameWin();
        } else {
            this.status = new Active();
        }
    }

    @JsonIgnore
    public GameBoard getBoard() {
        return this.board;
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

    @JsonProperty
    public GameStatus getGameStatus() {
        return this.status;
    }

    @JsonProperty
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}

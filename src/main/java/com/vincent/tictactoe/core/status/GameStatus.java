package com.vincent.tictactoe.core.status;

/**
 * ADT for game status. The corresponding Haskell would be:
 *  data GameStatus = Available | Active | Over | Tie
 */
public abstract class GameStatus {
    public abstract String getMessage();
}

package com.vincent.tictactoe.core.status;

/**
 * ADT for game status. The corresponding Haskell would be:
 *  data GameStatus = Available | Active | Over | Tie
 *
 *  getMessage :: GameStatus -> String
 *  getMessage Available = ...
 *  getMessage Active = ...
 *  ...
 */
public abstract class GameStatus {
    public abstract String getMessage();
}

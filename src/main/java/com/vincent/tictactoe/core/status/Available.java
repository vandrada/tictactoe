package com.vincent.tictactoe.core.status;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A subclass of `GameStatus`. Available indicates that a Game can be joined.
 */
public class Available extends GameStatus {

    @Override
    @JsonProperty
    public String getMessage() {
        return "Game is available to join";
    }

    @Override
    public boolean equals(Object o) {
        return o == this || (o instanceof Available);
    }
}

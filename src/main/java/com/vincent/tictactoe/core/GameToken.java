package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A GameToken is returned whenever a new game is created or joined. The
 * token is returned in the JSON but is not maintained by the GameManager.
 */
public class GameToken {
    private Game game;
    private Player player;
    private Tokenizer tokenizer;
    private String token;

    public GameToken(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.tokenizer = new Tokenizer(player);
        this.token = this.getToken();
        this.player.setToken(token);
    }

    @JsonProperty
    public String getToken() {
        return this.tokenizer.getToken();
    }

    @JsonProperty
    public Game getGame() {
        return this.game;
    }
}

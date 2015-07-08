package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A listing of available games
 * {
 *  "games": {
 *      ...
 *      }
 *  }
 */
public class Listing {
    private List<Game> games;
    private AtomicLong counter;

    public Listing() {
        //...
   }

    public Listing(List<Game> games) {
        this.games = new LinkedList<Game>(games);
        this.counter = new AtomicLong();
    }

    public Listing(Game[] games) {
        this(Arrays.asList(games));
    }

    public Game createGame(Player firstPlayer, Player secondPlayer) {
        return new Game(this.counter.getAndIncrement(), firstPlayer, secondPlayer);
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void removeGame(Game game) {
        this.games.remove(game);
    }

    @JsonProperty
    public Game[] getGames() {
        return this.games.toArray(new Game[this.games.size()]);
    }
}

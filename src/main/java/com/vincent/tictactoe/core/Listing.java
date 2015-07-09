package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A listing of available games
 * {
 *  "games":{
 *          {
 *              game1,
 *              game2,
 *              ...
 *          }
 *      }
 *  }
 */
public class Listing {
    private Map<Long, Game> games;
    private AtomicLong counter;

    public Listing() {
        //...
   }

    public Listing(List<Game> games) {
        this.games = new HashMap<Long, Game>();
        for (Game g : games) {
            this.games.put(g.getId(), g);
        }
        // this counter is responsible for creating games
        this.counter = new AtomicLong();
    }

    public Listing(Game[] games) {
        this(Arrays.asList(games));
    }

    public Game createGame(Player firstPlayer, Player secondPlayer) {
        return new Game(this.counter.getAndIncrement(), firstPlayer, secondPlayer);
    }

    public Game updateGame(long id, Player player) {
        if (this.games.containsKey(id)) {
            Game game = this.games.get(id);
            if (game.joinable()) {
                this.games.put(id, new Game(game.getId(),
                    game.getFirstPlayer(),
                    player));
                // since `put` returns the *previous* value...
                return this.games.get(id);
            }
            // TODO handle error!
        }

        return null;
    }

    public void addGame(Game game) {
        this.games.put(game.getId(), game);
    }

    public void removeGame(Game game) {
        this.games.remove(game.getId());
    }

    @JsonProperty
    public Game[] getGames() {
        return this.games.values().toArray(new Game[this.games.size()]);
    }
}

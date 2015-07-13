package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This Game manages multiple games and provides the only means to adding new
 * games, removing a game, and update a game. It's JSON representation is
 * just an array of games similar to:
 * {"games":[
 *          {game1},
 *          {game2},
 *           ...
 *          ]
 * }
 */
public class GameManager {
    private Map<Long, Game> games;
    private AtomicLong counter;

    public GameManager() {

    }

    public GameManager(List<Game> games) {
        this.games = new HashMap<Long, Game>();
        for (Game g : games) {
            this.games.put(g.getId(), g);
        }
        // this counter is responsible for creating games
        this.counter = new AtomicLong();
    }

    public GameManager(Game[] games) {
        this(Arrays.asList(games));
    }

    public Game createGame(Player firstPlayer) {
        return new Game(this.counter.getAndIncrement(), firstPlayer, null);
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

    public Game getGame(long id) {
        return this.games.get(id);
    }
}

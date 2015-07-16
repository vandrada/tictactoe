package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This Game manages multiple games and provides the only means to adding new
 * games, removing a game, and updateBoard a game. It's JSON representation is
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
        this.games = new HashMap<>();
        this.counter = new AtomicLong();
    }

    /**
     * Creates a new Game and adds it to the manager. Assigns the Game an ID
     * @param firstPlayer the first player of the game
     * @return a newly created Game
     */
    public Game createGame(Player firstPlayer) {
        Game game = new Game(this.counter.getAndIncrement(), firstPlayer, null);
        this.games.put(game.getId(), game);
        return game;
    }

    /**
     * Adds a second player to a Game
     * @param id the id of the Game to add a second player to
     * @param player the player to add
     * @return the Game after adding the second player or null
     */
    public Game addSecondPlayer(long id, Player player) {
        if (this.games.containsKey(id)) {
            Game game = this.games.get(id);
            if (game.joinable()) {
                this.games.put(id, new Game(game.getId(),
                    game.getFirstPlayer(),
                    player));
                this.games.get(id).determineGameStatus();
                // since `put` returns the *previous* value...
                return this.games.get(id);
            }
        }

        return null;
    }

    /**
     * Removes a Game from the GameManager
     * @param game the game to remove
     */
    public void removeGame(Game game) {
        this.games.remove(game.getId());
    }

    /**
     * Fetches a Game from the GameManager that has id {@code id}
     * @param id the id of the Game to fetch
     * @return the Game with id {@code id}
     */
    public Game getGame(long id) {
        return this.games.get(id);
    }

    /**
     * @return the Games in the GameManager
     */
    @JsonProperty
    public Game[] getGames() {
        return this.games.values().toArray(new Game[this.games.size()]);
    }
}

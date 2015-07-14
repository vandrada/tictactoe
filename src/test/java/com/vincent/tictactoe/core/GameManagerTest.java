package com.vincent.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameManagerTest {
    GameManager manager;
    Game game;

    @Before
    public void setUp() {
        manager = new GameManager();
        game = manager.createGame(new Player());
    }

    @Test
    public void createGame() {
        assertEquals("Created games should be automatically stored",
            game, manager.getGame(game.getId()));
    }

    @Test
    public void addSecondPlayer() {
        manager.addSecondPlayer(game.getId(), new Player("You"));
        assertNotNull("2nd player shouldn't be null after adding one",
            manager.getGame(game.getId()).getSecondPlayer());
    }

    @Test
    public void deleteAGame() {
        manager.removeGame(game);
        assertNull(manager.getGame(game.getId()));
    }

    @Test
    public void addMultiple() {
        manager.createGame(new Player());
        manager.createGame(new Player());

        assertEquals(3, manager.getGames().length);
    }

    @Test
    public void noIdReturnsNull() {
        assertNull("Invalid ID should return null", manager.getGame(1232132));
    }
}
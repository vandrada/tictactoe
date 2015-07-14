package com.vincent.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameTokenTest  {
    GameToken token;
    Game game;
    Player player;

    @Before
    public void setup() {
        player = new Player("Me");
        game = new Game(0, player, null);
        token = new GameToken(game, player);
    }

    @Test
    public void testGetToken() {
        String t = token.getToken();
        assertEquals("Token should be same between calls", t, token.getToken());
    }

    @Test
    public void getGame() {
        assertEquals("Game should be tied to token", game, token.getGame());
    }

    @Test
    // reasonably random at least
    public void tokensShouldBeRandom() {
        for (int i = 0; i < 100000; i++) {
            assertNotEquals(token.getToken(), new GameToken(game, player));
        }
    }
}
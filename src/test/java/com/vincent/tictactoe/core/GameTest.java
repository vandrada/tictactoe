package com.vincent.tictactoe.core;

import com.vincent.tictactoe.core.status.Active;
import com.vincent.tictactoe.core.status.Available;
import com.vincent.tictactoe.core.status.GameWin;
import com.vincent.tictactoe.core.status.Tie;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the basics of a Game
 */
public class GameTest {
    Player player1;
    Player player2;
    Game game;

    @Before
    public void setUp() {
        player1 = new Player("Me");
        player2 = new Player("You");
        game = new Game(0, player1, player2);
    }

    /*
     * Game Status
     */
    /* Available */
    @Test
    public void initialStatus() {
        Game game = new Game(0, player1, null);
        assertEquals("Game with one player should be Available",
            new Available(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    /* Active */
    @Test
    public void statusWithTwoPlayers() {
        assertEquals("Game with two players should be Active",
            new Active(), game.getGameStatus());
        assertTrue(game.isActive());
    }

    /* GameWin */
    @Test
    public void gameOverDiagonalOne() {
        game.updateBoard(Position.TOP_LEFT, "X");
        game.updateBoard(Position.CENTER, "X");
        game.updateBoard(Position.BOTTOM_RIGHT, "X");

        assertEquals("Game with winning board should be GameWin",
            new GameWin(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    @Test
    public void gameOverDiagonalTwo() {
        game.updateBoard(Position.TOP_RIGHT, "X");
        game.updateBoard(Position.CENTER, "X");
        game.updateBoard(Position.BOTTOM_LEFT, "X");

        assertEquals("Game with winning board should be GameWin",
            new GameWin(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    @Test
    public void gameOverHorizontal() {
        game.updateBoard(Position.TOP_CENTER, "X");
        game.updateBoard(Position.TOP_LEFT, "X");
        game.updateBoard(Position.TOP_RIGHT, "X");

        assertEquals("Game with horizontal win should be GameWin",
            new GameWin(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    @Test
    public void gameOverVertical() {
        game.updateBoard(Position.TOP_LEFT, "X");
        game.updateBoard(Position.CENTER_LEFT, "X");
        game.updateBoard(Position.BOTTOM_LEFT, "X");

        assertEquals("Game with vertical win should be GameWin",
            new GameWin(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    @Test
    public void gameFullAndWin() {
        game.updateBoard(Position.TOP_LEFT, "X");
        game.updateBoard(Position.TOP_CENTER, "O");
        game.updateBoard(Position.TOP_RIGHT, "X");
        // center
        game.updateBoard(Position.CENTER_LEFT, "O");
        game.updateBoard(Position.CENTER, "X");
        game.updateBoard(Position.CENTER_RIGHT, "O");
        // bottom
        game.updateBoard(Position.BOTTOM_LEFT, "O");
        game.updateBoard(Position.BOTTOM_CENTER, "X");
        game.updateBoard(Position.BOTTOM_RIGHT, "X");

        assertEquals("Game with vertical win should be GameWin",
            new GameWin(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    /* Tie */
    @Test
    public void gameTie() {
        // top
        game.updateBoard(Position.TOP_LEFT, "O");
        game.updateBoard(Position.TOP_CENTER, "X");
        game.updateBoard(Position.TOP_RIGHT, "X");
        // center
        game.updateBoard(Position.CENTER_LEFT, "X");
        game.updateBoard(Position.CENTER, "X");
        game.updateBoard(Position.CENTER_RIGHT, "O");
        // bottom
        game.updateBoard(Position.BOTTOM_LEFT, "O");
        game.updateBoard(Position.BOTTOM_CENTER, "O");
        game.updateBoard(Position.BOTTOM_RIGHT, "X");

        assertEquals("Game with vertical win should be GameWin",
            new Tie(), game.getGameStatus());
        assertFalse(game.isActive());
    }

    /* Board with no winner should be Active */

    @Test
    public void gameStillOn() {
        game.updateBoard(Position.CENTER, "X");
        game.updateBoard(Position.TOP_CENTER, "X");
        game.updateBoard(Position.BOTTOM_CENTER, "O");

        assertEquals("Game with no winner (yet) should be Active",
            new Active(), game.getGameStatus());
        assertTrue(game.isActive());
    }

    /*
     * Other methods
     */
    @Test
    public void testCurrentPlayerNotNull() {
        assertNotNull("Current Player should never be null",
            game.getCurrentPlayer());
        game.updateBoard(Position.TOP_CENTER, "X");
        assertNotNull("Current Player should never be null",
            game.getCurrentPlayer());
    }

    @Test
    public void testCurrentPlayerChanges() {
        Player temp = game.getCurrentPlayer();
        game.updateBoard(Position.CENTER, "Y");
        assertNotEquals("Current Player should change",
            temp, game.getCurrentPlayer());
    }

    @Test
    public void allPositionsEmpty() {
        for (Position pos : Position.values()) {
            assertTrue(game.positionEmpty(pos));
        }
    }

    @Test
    public void testOtherPlayer() {
        Player p = game.getCurrentPlayer();
        assertNotEquals("otherPlayer should change player",
            p, game.otherPlayer());
    }

    @Test
    public void testTokens() {
        player1.setToken("ajsdhequ");
        player2.setToken("iowqyerqwouy");
        assertTrue("Player 1 should have a valid token",
            game.validToken(player1.getToken()));
        assertTrue("Player 2 should have a valid token",
            game.validToken(player2.getToken()));
    }

    @Test
    public void testGetToken() {
        String token = "asjhfd";
        player1.setToken(token);
        assertEquals(player1, game.getPlayerByToken(token).get());
    }
}

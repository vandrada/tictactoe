package com.vincent.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GameBoard for wins and ties
 */
public class GameBoardTest {
    @Test
    public void testDiagonal() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_LEFT, "X");
        gb.mark(Position.CENTER, "X");
        gb.mark(Position.BOTTOM_RIGHT, "X");

        assertEquals(true, gb.check());
    }

    @Test
    public void testOtherDiagonal() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_RIGHT, "X");
        gb.mark(Position.CENTER, "X");
        gb.mark(Position.BOTTOM_LEFT, "X");

        assertEquals(true, gb.check());
    }

    @Test
    public void testVertical() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.BOTTOM_LEFT, "X");
        gb.mark(Position.TOP_LEFT, "X");
        gb.mark(Position.CENTER_LEFT, "X");

        assertEquals(true, gb.check());
    }

    @Test
    public void testHorizontal() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_CENTER, "X");
        gb.mark(Position.TOP_LEFT, "X");
        gb.mark(Position.TOP_RIGHT, "X");

        assertEquals(true, gb.check());
    }

    /*
     * gb.check() should fail...
     */

    @Test
    public void testDiagonalFail() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_LEFT, "Y");
        gb.mark(Position.CENTER, "X");
        gb.mark(Position.BOTTOM_RIGHT, "X");

        assertEquals(false, gb.check());
    }

    @Test
    public void testHorizontalFail() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_CENTER, "X");
        gb.mark(Position.TOP_LEFT, "O");
        gb.mark(Position.TOP_RIGHT, "X");

        assertEquals(false, gb.check());
    }

    @Test
    public void testRandom() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.TOP_LEFT, "O");
        gb.mark(Position.CENTER_LEFT, "O");
        gb.mark(Position.CENTER, "X");
        gb.mark(Position.CENTER_RIGHT, "X");
        gb.mark(Position.BOTTOM_LEFT, "X");

        assertEquals(false, gb.check());
    }

    @Test
    public void testVerticalFail() {
        GameBoard gb = new GameBoard();
        gb.mark(Position.BOTTOM_LEFT, "X");
        gb.mark(Position.TOP_LEFT, "O");
        gb.mark(Position.CENTER_LEFT, "X");

        assertEquals(false, gb.check());
    }
}
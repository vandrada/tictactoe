package com.vincent.tictactoe.core.status;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the `equals` implementation in each subclass of GameStatus. Each
 * subclass should only be equal to the same class.
 */
public class GameStatusTest {
    GameStatus active;
    GameStatus available;
    GameStatus win;
    GameStatus tie;

    @Before
    public void setUp() {
        active = new Active();
        available = new Available();
        win = new GameWin();
        tie = new Tie();
    }

    @Test
    public void testActive() {
        assertNotEquals(active, available);
        assertNotEquals(active, win);
        assertNotEquals(active, tie);
        assertEquals(active, active);
    }

    @Test
    public void testAvailable() {
        assertNotEquals(available, active);
        assertNotEquals(available, win);
        assertNotEquals(available, tie);
        assertEquals(available, available);
    }

    @Test
    public void testWin() {
        assertNotEquals(win, available);
        assertNotEquals(win, active);
        assertNotEquals(win, tie);
        assertEquals(win, win);
    }

    @Test
    public void testTie() {
        assertNotEquals(tie, available);
        assertNotEquals(tie, active);
        assertNotEquals(tie, win);
        assertEquals(tie, tie);
    }
}
package com.vincent.tictactoe.core;

import org.junit.Before;

public class MoveTest {
    Move move;
    Player player = new Player("Juan");
    Player player2 = new Player("John");
    Game game = new Game(0, player, player2);

    @Before
    public void setup() {
        move = new Move(game, game.getPlayerByToken(player.getToken()),
            Position.BOTTOM_CENTER);
    }

    // @Test
    // public void testMoveConstructor() {
    //     System.out.println(game);
    // }

}
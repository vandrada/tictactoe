package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.GameBoard;
import com.vincent.tictactoe.core.GameManager;
import com.vincent.tictactoe.core.Move;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class GamePlayResource {
    private final GameManager gameManager;

    public GamePlayResource(GameManager listing) {
        this.gameManager = listing;
    }

    // TODO finish
    @GET
    @Timed
    @Path("{id}/{token}/move")
    public Move mark(@PathParam("token") String token,
                     @PathParam("game") long id
                     /*@QueryParam("pos") GameBoard.Positions pos*/) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token)) {
            System.out.println("valid");
            return new Move(game, game.getPlayerByToken(token), null);
        }
        System.out.println("not valid" + "tokens are " + game.getFirstPlayer()
            .getToken() + " and " + game.getSecondPlayer().getToken());
        return null;
    }

    @GET
    @Timed
    @Path("{id}/{token}/board")
    public GameBoard getBoard(@PathParam("token") String token,
                              @PathParam("id") long id) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token)) {
            return game.getBoard();
        }
        return null;
    }

    /**
     * Returns information about the Game such as the current board
     * configuration, the players involved, and who's move it is.
     */
    @GET
    @Path("/{id}")
    public Game getInfo(@PathParam("id") long id) {
        return this.gameManager.getGame(id);
    }
}

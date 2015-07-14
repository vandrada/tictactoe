package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class GamePlayResource {
    private final GameManager gameManager;

    public GamePlayResource(GameManager listing) {
        this.gameManager = listing;
    }

    @GET
    @Timed
    @Path("{id}/{token}/move")
    public Move mark(@PathParam("token") String token,
                     @PathParam("game") long id,
                     @QueryParam("pos") int pos) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token)) {
            return new Move(game, game.getPlayerByToken(token),
                            Position.values()[pos]);
        }
        // TODO return something else
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

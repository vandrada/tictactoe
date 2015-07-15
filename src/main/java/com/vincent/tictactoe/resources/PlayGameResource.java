package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class PlayGameResource {
    private final GameManager gameManager;

    public PlayGameResource(GameManager listing) {
        this.gameManager = listing;
    }

    @GET
    @Timed
    @Path("/{id}/{token}/move")
    public Move mark(@PathParam("token") String token,
                     @PathParam("game") long id,
                     @QueryParam("pos") int pos) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token) && game.isActive()) {
            return new Move(game, game.getPlayerByToken(token),
                            Position.values()[pos]);
        }
        return null;
    }

    /**
     * Returns information about the Game such as the current board
     * configuration, the players involved, and who's move it is.
     */
    @GET
    @Path("/{id}/{token}")
    public GameInfo getInfo(@PathParam("id") long id,
                        @PathParam("token") String token) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token)) {
            return new GameInfo(game);
        }
        return null;
    }
}

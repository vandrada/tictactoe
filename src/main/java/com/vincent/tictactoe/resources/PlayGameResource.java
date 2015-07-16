package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
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
    public Optional<Move> mark(@PathParam("token") String token,
                     @PathParam("id") long id,
                     @QueryParam("pos") int pos) {
        Game game = gameManager.getGame(id);
        if (game.validToken(token) && game.isActive()) {
            return Optional.of(new Move(game,
                game.getPlayerByToken(token).get(),
                Position.values()[pos]));
        }
        return Optional.absent();
    }

    /**
     * Returns information about the Game such as the current board
     * configuration, the players involved, and who's move it is.
     */
    @GET
    @Path("/{id}/{token}")
    public Optional<GameInfo> getInfo(@PathParam("id") long id,
                            @PathParam("token") String token) {
        Game game = gameManager.getGame(id);
        System.out.println(game.getId());
        if (game.validToken(token)) {
            return Optional.of(new GameInfo(game));
        }
        return Optional.absent();
    }
}

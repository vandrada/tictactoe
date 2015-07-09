package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.GameBoard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/play")
@Produces(MediaType.APPLICATION_JSON)
public class GamePlayResource {
    private final Game game;

    public GamePlayResource(Game game) {
        this.game = game;
    }

    @GET
    @Timed
    public void mark(@QueryParam("pos") GameBoard.Positions pos) {
        game.update(pos, 'X');
    }
}

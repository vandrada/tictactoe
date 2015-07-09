package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.GameManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/list")
@Produces(MediaType.APPLICATION_JSON)
public class GameListingResource {
    private final GameManager gameManager;

    public GameListingResource(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GET
    @Timed
    public GameManager gameListing(){
        // TODO funky...
        return new GameManager(this.gameManager.getGames());
    }
}

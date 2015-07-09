package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.Listing;
import com.vincent.tictactoe.core.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/new-game")
@Produces(MediaType.APPLICATION_JSON)
public class NewGameResource {
    private final Listing listing;

    public NewGameResource(Listing listing) {
        this.listing = listing;
    }

    @GET
    @Timed
    // Creates a new game and returns it as JSON
    public Game newGame(@QueryParam("name") String player1) {
        Game game = listing.createGame(new Player(player1), null);
        this.listing.addGame(game);
        return game;
    }
}

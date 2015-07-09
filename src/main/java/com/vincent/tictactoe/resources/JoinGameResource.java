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

@Path("/join-game")
@Produces(MediaType.APPLICATION_JSON)
public class JoinGameResource {
    private Listing listing;

    public JoinGameResource(Listing listing) {
        this.listing = listing;
    }

    @GET
    @Timed
    public Game joinGame(@QueryParam("id") long id,
                         @QueryParam("name") String name) {
       return listing.updateGame(id, new Player(name));
    }
}

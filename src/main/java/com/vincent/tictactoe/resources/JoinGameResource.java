package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
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
    private final String player2Default;

    public JoinGameResource(Listing listing, String player2Default) {
        this.listing = listing;
        this.player2Default = player2Default;
    }

    @GET
    @Timed
    public Game joinGame(@QueryParam("id") long id,
                         @QueryParam("name") Optional<String> name) {
       return listing.updateGame(id, new Player(name.or(player2Default)));
    }
}

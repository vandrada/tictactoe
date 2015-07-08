package com.vincent.tictactoe.resources;

import com.vincent.tictactoe.core.Listing;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/join-game")
@Produces(MediaType.APPLICATION_JSON)
public class JoinGameResource {
    private Listing listing;

    public JoinGameResource(Listing listing) {
        this.listing = listing;
    }
}

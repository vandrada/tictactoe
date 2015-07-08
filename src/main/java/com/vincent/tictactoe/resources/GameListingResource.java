package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.vincent.tictactoe.core.Listing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tic-tac-toe/")
@Produces(MediaType.APPLICATION_JSON)
public class GameListingResource {
    private final Listing listing;

    public GameListingResource(Listing listing) {
        this.listing =  listing;
    }

    @GET
    @Timed
    public Listing gameListing(){
        return new Listing(this.listing.getGames());
    }
}

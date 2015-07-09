package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.GameManager;
import com.vincent.tictactoe.core.GameToken;
import com.vincent.tictactoe.core.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/join-game")
@Produces(MediaType.APPLICATION_JSON)
public class JoinGameResource {
    private GameManager gameManager;
    private final String player2Default;

    public JoinGameResource(GameManager gameManager, String player2Default) {
        this.gameManager = gameManager;
        this.player2Default = player2Default;
    }

    @GET
    @Timed
    public GameToken joinGame(@QueryParam("id") long id,
                         @QueryParam("name") Optional<String> name) {
        Player player = new Player(name.or(player2Default));
        Game game = gameManager.updateGame(id, player);

        return new GameToken(game, player);
    }
}

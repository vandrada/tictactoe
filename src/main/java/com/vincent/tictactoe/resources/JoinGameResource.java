package com.vincent.tictactoe.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.GameManager;
import com.vincent.tictactoe.core.GameToken;
import com.vincent.tictactoe.core.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Random;

@Path("/join-game")
@Produces(MediaType.APPLICATION_JSON)
public class JoinGameResource {
    private GameManager gameManager;
    private final String player2Default;
    private final String player2Mark;

    public JoinGameResource(GameManager gameManager, String player2Default,
                            String player2Mark) {
        this.gameManager = gameManager;
        this.player2Default = player2Default;
        this.player2Mark = player2Mark;
    }

    @GET
    @Timed
    @Path("/{id}")
    public GameToken joinGame(@PathParam("id") long id,
                              @QueryParam("name") Optional<String> name) {
        Player player = new Player(name.or(player2Default));
        player.setMark(this.player2Mark);
        Game game = gameManager.addSecondPlayer(id, player);

        return new GameToken(game, player);
    }

    @GET
    @Timed
    @Path("/random")
    public GameToken joinRandomGame(@QueryParam("name") Optional<String> name) {
        Player player = new Player(name.or(player2Default));
        player.setMark(this.player2Mark);

        Random rand = new Random();
        long randomId = rand.nextInt(gameManager.getGames().length);
        Game game = gameManager.getGame(randomId);
        gameManager.addSecondPlayer(randomId, player);

        return new GameToken(game, player);
    }
}

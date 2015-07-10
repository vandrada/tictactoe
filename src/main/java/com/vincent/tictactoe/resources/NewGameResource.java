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

@Path("/new-game")
@Produces(MediaType.APPLICATION_JSON)
public class NewGameResource {
    private final GameManager gameManager;
    private final String player1Default;
    private final String player1Mark;

    public NewGameResource(GameManager gameManager, String player1Default,
                           String player1Mark) {
        this.gameManager = gameManager;
        this.player1Default = player1Default;
        this.player1Mark = player1Mark;
    }

    @GET
    @Timed
    public GameToken newGame(@QueryParam("name") Optional<String> player1) {
        Player player = new Player(player1.or(player1Default));
        player.setMark(this.player1Mark);
        Game game = gameManager.createGame(player);
        GameToken gameToken = new GameToken(game, player);

        this.gameManager.addGame(game);
        return gameToken;
    }
}

package com.vincent.tictactoe;

import com.vincent.tictactoe.core.Game;
import com.vincent.tictactoe.core.Listing;
import com.vincent.tictactoe.resources.GameListingResource;
import com.vincent.tictactoe.resources.JoinGameResource;
import com.vincent.tictactoe.resources.NewGameResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TicTacToeApplication extends Application<TicTacToeConfiguration> {
    public static void main(String[] args) throws Exception {
        new TicTacToeApplication().run(args);
    }

    @Override
    public String getName() {
        return "tic-tac-toe";
    }

    @Override
    public void initialize(Bootstrap<TicTacToeConfiguration> bootstrap) {
        // ...
    }

    @Override
    public void run(TicTacToeConfiguration configuration,
                    Environment environment) {
        // Acts as a global variable or singleton for the entire application
        // Contains a listing of all games and provides the mechanism to add
        // games, remove games, and join a game
        Listing listing = new Listing(new Game[]{});

        final NewGameResource newGame = new NewGameResource(listing);
        environment.jersey().register(newGame);

        final JoinGameResource joinGame = new JoinGameResource(listing);
        environment.jersey().register(joinGame);

        final GameListingResource gameListing = new GameListingResource(listing);
        environment.jersey().register(gameListing);

        // TODO health check
    }

}

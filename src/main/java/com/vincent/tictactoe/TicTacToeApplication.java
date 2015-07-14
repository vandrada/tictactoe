package com.vincent.tictactoe;

import com.vincent.tictactoe.core.GameManager;
import com.vincent.tictactoe.resources.GameListingResource;
import com.vincent.tictactoe.resources.GamePlayResource;
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
        final GameManager gameManager = new GameManager();

        final NewGameResource newGame = new NewGameResource(gameManager,
            configuration.getFirstPlayerDefault(),
            configuration.getFirstPlayerMark()) ;
        environment.jersey().register(newGame);

        final JoinGameResource joinGame = new JoinGameResource(gameManager,
            configuration.getSecondPlayerDefault(),
            configuration.getSecondPlayerMark());
        environment.jersey().register(joinGame);

        final GameListingResource gameListing = new GameListingResource(gameManager);
        environment.jersey().register(gameListing);

        final GamePlayResource gamePlay = new GamePlayResource(gameManager);
        environment.jersey().register(gamePlay);

        // TODO health check
    }

}

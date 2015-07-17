# RESTful Tic-tac-toe

## Building and Running
- `cd` into `tictactoe/`
- execute `mvn package` to build the fat JAR
- run the JAR with `java -jar target/tictactoe-1.0-SNAPSHOT.jar server config.yml`

## Commands
- `/list`: list the current games

### Creating a Game
- `/new-game`: creates a new game and adds you as the first player with the name
  "Player 1". In the response a token is returned. This token must be passed to
  play the game
- `/new-game?name={name}`: similar to `new-game`, but assigns the name `name` to
  the first player.

The JSON returned from creating a game is:

```
{
  "game": {
    "id": <Game-id>,
    "currentPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "firstPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "secondPlayer": null,
    "gameStatus": {
      "message": "Game is available to join"
    }
  },
  "token": <First-player-token>
}
```

### Joining a Game
- `/join-game/{id}`: joins the game with the id of `id` and adds
   you as the second player with the name "Player 2". In the response a token is
   returned. This token must be passed to play the game.
- `/join-game/{id}?name={name}`: similar to `join-game`, but assigns the name
  `name` to the second player

The JSON returned from joining a game is:

```
{
  "game": {
    "id": <Game-id>,
    "currentPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "firstPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "secondPlayer": {
      "name": <Player-name>,
      "mark": "O"
    },
    "gameStatus": {
      "message": "Game is currently active"
    }
  },
  "token": <Second-player-token>
}
```

### Playing the Game
- `/play/{id}/{token}`: gets information about the game such as the current
   player and the current board configuration. `token` must be a token from an
   active player.

The game's information is represented in JSON as:
```
{
  "game": {
    "id": <Game-id>,
    "currentPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "firstPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "secondPlayer": {
      "name": <Player-name>,
      "mark": "O"
    },
    "gameStatus": {
      "message": "Game is currently active"
    }
  },
  "gameBoard": {
    "board": [
      [" ", " ", " "],
      [" ", " ", " "],
      [" ", " ", " "]
    ]
  }
}
```

- `/play/{id}/{token}/move?pos={pos}`: mark the board at `pos` with your mark.
   The player is determined by the passed in token. The returned JSON returns
   the game that the move was made in and whether or not the move was valid.
   Positions are determined by a number. The number of the positions on the
   board are:

```
+---+---+---+
| 0 | 1 | 2 |
+---+---+---+
| 3 | 4 | 5 |
+---+---+---+
| 6 | 7 | 8 |
+---+---+---+
```

The response returned after a move is:
```
{
  "game": {
    "id": <Game-id>,
    "currentPlayer": {
      "name": <Player-name>,
      "mark": "O"
    },
    "firstPlayer": {
      "name": <Player-name>,
      "mark": "X"
    },
    "secondPlayer": {
      "name": <Player-name>,
      "mark": "O"
    },
    "gameStatus": {
      "message": "Game is currently active"
    }
  },
  "player": {
    "name": <Player-name>,
    "mark": "X"
  },
  "pos": "TOP_LEFT",
  "valid": true,
  "board": {
    "board": [
      ["X", " ", " "],
      [" ", " ", " "],
      [" ", " ", " "]
    ]
  }
}
```

## Defaults
- firstPlayerDefault: the default name for Player 1, the default is "Player 1"
- secondPlayerDefault: the default name for Player 2, the default is "Player 2"
- firstPlayerMark: the default mark for Player 1, the default is "X"
- secondPlayerMark: the default mark for Player 2, the default is "Y"

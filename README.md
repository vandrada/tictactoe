# RESTful Tic-tac-toe
Candidate project for Main Street Hub

## Building and Running
- `cd` into `tictactoe/`
- execute `mvn package`

## Commands
- `list`: list the current games
- `new-game<?name={name}>`: creates a new game and adds you as the first player
   with the name `name`. If no name is supplied the default name of "Player1"
   is used. In the response a token is returned. This token must be passed to
   play the game
- `join-game/{id}<&name={name}>`: joins the game with the id of `id` and adds
   you as the second player with the name `name`. If no name is supplied the
   default name of "Player2" is used. In the response a token is returned. This
   token must be passed to play the game.
- `play/{id}/{token}`: gets information about the game such as the current
   player and the current board configuration. `token` must be a token from an
   active player.
- `play/{id}/{token}/move?pos={pos}`: mark the board at `pos` with your mark.
   The mark is determined by the passed in token. The returned JSON returns the
   game that the move was made in and whether or not the move was valid. Moves
   are determined by a number. The number of the positions on the board are:

```
+---+---+---+
| 0 | 1 | 2 |
+---+---+---+
| 3 | 4 | 5 |
+---+---+---+
| 6 | 7 | 8 |
+---+---+---+
```

## Defaults
- firstPlayerDefault: the default name for Player 1, the default is "Player 1"
- secondPlayerDefault: the default name for Player 2, the default is "Player 2"
- firstPlayerMark: the default mark for Player 1, the default is "X"
- secondPlayerMark: the default mark for Player 2, the default is "Y"

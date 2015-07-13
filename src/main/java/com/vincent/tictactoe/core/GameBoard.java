package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  A game board for tic-tac-toe
 *  +---+---+---+
 *  | 0 | 1 | 2 |
 *  +---+---+---+
 *  | 3 | 4 | 5 |
 *  +---+---+---+
 *  | 6 | 7 | 8 |
 *  +---+---+---+
 */
public class GameBoard {
    // Java's default char value
    private static char EMPTY = '\u0000';
    char[][] board;

    public GameBoard() {
        board = new char[3][3];
    }

    /**
     * Checks if a game is over
     * @return true if the game is over; false otherwise
     */
    public boolean check() {
        return (checkHorizontal() || checkVertical() || checkDiagonal());
    }

    /**
     * Checks if the board is full and no new marks can be placed
     * @return true if the board is full; false otherwise
     */
    public boolean full() {
        for (Position pos : Position.values()) {
            if (board[pos.getX()][pos.getY()] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Marks the board at the specified position
     * @param pos the position on the board to mark
     * @param mark the mark to place
     */
    public void mark(Position pos, char mark) {
        if (board[pos.getX()][pos.getY()] == EMPTY) {
            board[pos.getX()][pos.getY()] = mark;
        }
    }

    /**
     * Checks if three entries are the same
     * @param c1 the first entry to check
     * @param c2 the second entry to check
     * @param c3 the third entry to check
     * @return true of c1, c2, and c3 are equal; false otherwise
     */
    private boolean checkEntries(char c1, char c2, char c3) {
        return (c1 != EMPTY && (c1 == c2 && c2 == c3));
    }

    /**
     * Checks a column to see if all entries are the same
     * @return true if they are all the same; false otherwise
     */
    private boolean checkVertical() {
        for (int i = 0; i < 3; i++) {
            if (checkEntries(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks a row to see if all entries are the same
     * @return true of they are all the same; false otherwise
     */
    private boolean checkHorizontal() {
        for (int i = 0; i < 3; i++) {
            if (checkEntries(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the two diagonals in the board to see if they are the same
     * @return true if all entries are the same; false otherwise
     */
    private boolean checkDiagonal() {
        return checkEntries(board[0][0], board[1][1], board[2][2]) ||
            checkEntries(board[2][0], board[1][1], board[2][0]);
    }

    /*
     * Pretty printing the board
     */
    private String getEntry(Position pos) {
        if (board[pos.getX()][pos.getY()] == EMPTY) {
            Integer p = pos.ordinal();
            return p.toString();
        } else {
            return Character.toString(board[pos.getX()][pos.getY()]);
        }
    }

    // TODO ugly and not consistent!
    @JsonProperty
    public char[][] getBoard() {
        return this.board;
    }
}

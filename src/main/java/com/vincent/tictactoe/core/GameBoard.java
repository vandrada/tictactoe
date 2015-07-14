package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * A game board for tic-tac-toe represented as a 2-D array of Strings
 * +----+----+----+
 * | "" | "" | "" |
 * +----+----+----+
 * | "" | "" | "" |
 * +----+----+----+
 * | "" | "" | "" |
 * +----+----+----+
 * The public methods check if there's a winner, check if all the positions
 * are full, mark a position, and check if a position is open.
 */
public class GameBoard {
    // Java's default char value
    private static String EMPTY = " ";
    String[][] board;

    public GameBoard() {
        board = new String[3][3];
        // fill all entries to the default of EMPTY or ""
        for (String[] row : board) {
            Arrays.fill(row, EMPTY);
        }
    }

    /**
     * Checks if a game is over
     * @return true if the game is over; false otherwise
     */
    public boolean check() {
        return (checkHorizontal() || checkVertical() || checkDiagonal());
    }

    // TODO determine winner

    /**
     * Checks if the board is full and no new marks can be placed
     * @return true if the board is full; false otherwise
     */
    public boolean full() {
        for (Position pos : Position.values()) {
            if (board[pos.getX()][pos.getY()].equals(EMPTY)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Marks the board at the specified position
     * @param pos  the position on the board to mark
     * @param mark the mark to place
     */
    public void mark(Position pos, String mark) {
        if (board[pos.getX()][pos.getY()].equals(EMPTY)) {
            board[pos.getX()][pos.getY()] = mark;
        }
    }

    /**
     * Checks if a Position is empty
     * @param pos the position to check
     * @return true if the position is empty; false otherwise
     */
    public boolean isEmpty(Position pos) {
        return board[pos.getX()][pos.getY()].equals(EMPTY);
    }

    // TODO ugly and not consistent!
    @JsonProperty
    public String[][] getBoard() {
        return this.board;
    }

    /*
     * Private Functions
     */

    /**
     * Checks if three entries are the same
     * @param c1 the first entry to check
     * @param c2 the second entry to check
     * @param c3 the third entry to check
     * @return true of c1, c2, and c3 are equal; false otherwise
     */
    private boolean checkEntries(String c1, String c2, String c3) {
        return ((!c1.equals(EMPTY)) && (c1.equals(c2) && c2.equals(c3)));
    }

    /**
     * Checks each column to see if all entries are the same
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

    // TODO pretty print board
    private String getEntry(Position pos) {
        if (board[pos.getX()][pos.getY()].equals(EMPTY)) {
            Integer p = pos.ordinal();
            return p.toString();
        } else {
            return board[pos.getX()][pos.getY()];
        }
    }
}

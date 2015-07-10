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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Marks the board at the specified position
     * @param pos the position on the board to mark
     * @param mark the mark to place
     */
    public void mark(Positions pos, char mark) {
        switch (pos) {
            case TOP_LEFT:
                updateBoard(mark, 0, 0);
                break;
            case TOP_CENTER:
                updateBoard(mark, 0, 1);
                break;
            case TOP_RIGHT:
                updateBoard(mark, 0, 2);
                break;
            case CENTER_LEFT:
                updateBoard(mark, 1, 0);
                break;
            case CENTER:
                updateBoard(mark, 1, 1);
                break;
            case CENTER_RIGHT:
                updateBoard(mark, 1, 2);
                break;
            case BOTTOM_LEFT:
                updateBoard(mark, 2, 0);
                break;
            case BOTTOM_CENTER:
                updateBoard(mark, 2, 1);
                break;
            case BOTTOM_RIGHT:
                updateBoard(mark, 2, 2);
                break;
        }
    }

    /**
     * Positions on a 3 x 3 board, just to improve readability and avoid
     * off-by-one errors
     */
    public enum Positions {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT,
        CENTER_LEFT, CENTER, CENTER_RIGHT,
        BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT
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

    /**
     * Updates the board by placing a mark on the board
     * @param mark the type of mark to put
     * @param xpos the x position of the board
     * @param ypos the y position of the board
     */
    private void updateBoard(char mark, int xpos, int ypos) {
        if (board[xpos][ypos] == EMPTY) {
            board[xpos][ypos] = mark;
        }
    }

    /*
     * Pretty printing the board
     */
    private String getEntry(int x, int y) {
        if (board[x][y] == EMPTY) {
            return "*";
        } else {
            return Character.toString(board[x][y]);
        }
    }

    // TODO ugly and not consistent!
    @JsonProperty
    public String firstRow() {
        return String.format("%s | %s | %s", getEntry(0, 0), getEntry(0, 1),
            getEntry(0, 2));
    }

    @JsonProperty
    public String secondRow() {
        return String.format("%s | %s | %s", getEntry(1, 0), getEntry(1, 1),
            getEntry(1, 2)) + System.getProperty("line.separator");
    }

    @JsonProperty
    public String thirdRow() {
        return String.format("%s | %s | %s", getEntry(2, 0), getEntry(2, 1),
            getEntry(2, 2));
    }

}

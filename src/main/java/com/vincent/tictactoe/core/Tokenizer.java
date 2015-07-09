package com.vincent.tictactoe.core;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates pseudo-random tokens when a player creates/joins a game. These
 * tokens are required to be submitted along with a move in a game
 * of tic-tac-toe. The seed for the tokenizer is constructed from the
 * Player's name and the current time.
 */
public class Tokenizer {
    private static final int NUM_BYTES = 40;
    private static final int BASE = 32;
    private static final int BUF_SIZE = 4;
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    private Player player;
    private SecureRandom random;
    private StringBuilder seed;

    public Tokenizer(Player player) {
        this.player = player;
        this.seed = new StringBuilder();

        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        seed.append(this.player.getName());
        seed.append(formatter.format(new Date()));

        random = new SecureRandom(ByteBuffer.allocate(BUF_SIZE).
            putInt(seed.hashCode()).array());
    }

    /**
     * Returns a token created by the Player's name and the current date
     * @return a random String to be used as a Player's token
     */
    public String getToken() {
        return new BigInteger(NUM_BYTES, random).toString(BASE);
    }
}

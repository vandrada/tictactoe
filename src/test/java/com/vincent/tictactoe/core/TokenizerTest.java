package com.vincent.tictactoe.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TokenizerTest {
    Tokenizer tokenizer;

    @Before
    public void setup() {
        this.tokenizer = new Tokenizer(new Player());
    }

    @Test
    public void noDuplicates() {
        String token = tokenizer.getToken();
        assertNotEquals(token, tokenizer.getToken());
    }

    @Test(expected=NullPointerException.class)
    public void failsOnNull() {
        tokenizer = new Tokenizer(null);
    }
}
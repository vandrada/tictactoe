package com.vincent.tictactoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class TicTacToeConfiguration extends Configuration {
    @NotEmpty
    private String firstPlayerDefault = "Player1";

    @NotEmpty
    private String secondPlayerDefault = "Player2";

    @JsonProperty
    public String getSecondPlayerDefault() {
        return secondPlayerDefault;
    }

    @JsonProperty
    public String getFirstPlayerDefault() {
        return firstPlayerDefault;
    }

    @JsonProperty
    public void setFirstPlayerDefault(String firstPlayerDefault) {
        this.firstPlayerDefault = firstPlayerDefault;
    }

    @JsonProperty
    public void setSecondPlayerDefault(String secondPlayerDefault) {
        this.secondPlayerDefault = secondPlayerDefault;
    }
}

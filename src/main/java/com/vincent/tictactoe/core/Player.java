package com.vincent.tictactoe.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * A player in a tic-tac-toe game
 * {
 *     "name": _
 *     "active game": _
 * }
 */

@Entity
@Table(name = "people")
@NamedQueries({
    @NamedQuery(
        name = "com.vincent.tictactoe.core.Person.findAll",
        query = "SELECT p FROM Person p"
    )
})
public class Player {
    // a players screen-name
    @Column(name="name", nullable=false)
    private String name;
    // a players active game
    private Game activeGame;
    // a players password
    private String password;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    @JsonProperty
    public String getPassword() {
        return this.password;
    }

    /*@JsonProperty
    public Game getActiveGame() {
        return this.activeGame;
    }*/
}

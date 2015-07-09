package com.vincent.tictactoe.db;

import com.google.common.base.Optional;
import com.vincent.tictactoe.core.Player;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Vincent on 7/9/15.
 */
public class PlayerDAO extends AbstractDAO<Player> {
    public PlayerDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Player> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Player create(Player player) {
        return persist(player);
    }

    public List<Player> findAll() {
        return list(namedQuery("com.vincent.tictactoe.core.Player.findAll"));
    }
}

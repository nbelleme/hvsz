package io.resourcepool.hvsz.service.impl;


import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    DaoMapDb dao;

    @Override
    public List<Game> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Game> getAllRuningGames() {
        return dao.getAll().stream()
                .filter(game -> game.getStatus().getStarted())
                .collect(Collectors.toList());
    }


}

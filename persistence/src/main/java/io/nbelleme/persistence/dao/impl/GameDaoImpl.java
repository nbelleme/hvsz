package io.nbelleme.persistence.dao.impl;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.persistence.dao.api.GameDao;
import io.nbelleme.persistence.dao.api.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by nbelleme on 02/08/2017.
 */
@Repository
public class GameDaoImpl implements GameDao {
  private GameRepository gameRepository;

  GameDaoImpl(GameRepository gameRepository) {
    this.gameRepository = Objects.requireNonNull(gameRepository);
  }

  @Override
  public Optional<Game> findOne(String id) {
    return Optional.ofNullable(gameRepository.findOne(id));
  }

  @Override
  public Optional<List<Game>> findAll() {
    return Optional.ofNullable(gameRepository.findAll());
  }

  @Override
  public Optional<Game> insert(Game game) {
    return Optional.ofNullable(gameRepository.insert(game));
  }
}

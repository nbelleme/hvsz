package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.mapper.api.GameMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
final class GameMapperImpl implements GameMapper<Game, GameDTO> {

  //TODO implemnts

  private Mapper mapper;

  /**
   * Default constructor.
   *
   * @param mapper dozer mapper
   */
  GameMapperImpl(Mapper mapper) {
    this.mapper = Objects.requireNonNull(mapper);
  }

  @Override
  public GameDTO toDTO(Game game) {
    Objects.requireNonNull(game);
    return mapper.map(game, GameDTO.class);
  }

  @Override
  public Game fromDTO(GameDTO gameDTO) {
    Objects.requireNonNull(gameDTO);
    return mapper.map(gameDTO, Game.class);

  }
}

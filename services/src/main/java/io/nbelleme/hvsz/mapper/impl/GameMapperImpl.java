package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.mapper.api.GameMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
final class GameMapperImpl implements GameMapper {

  //TODO implemnts

  private Mapper mapper;

  /**
   * Default constructor.
   *
   * @param mapper dozer mapper
   */
  public GameMapperImpl(Mapper mapper) {
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

  @Override
  public List<GameDTO> toDTO(List<Game> games) {
    Objects.requireNonNull(games);

    return games.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
  }


  @Override
  public List<Game> fromDTO(List<GameDTO> games) {
    Objects.requireNonNull(games);

    return games.stream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
  }

}

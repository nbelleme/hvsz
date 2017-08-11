package io.nbelleme.hvsz.mapper.api;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;

import java.util.List;

public interface GameMapper {
  /**
   * Map {@link Game} to {@link GameDTO}.
   *
   * @param game game object to map
   * @return gameDTO mapped
   */
  GameDTO toDTO(Game game);

  /**
   * Unmap {@link GameDTO} to {@link Game}.
   *
   * @param gameDTO object to unmap
   * @return game unmapped
   */
  Game fromDTO(GameDTO gameDTO);

  /**
   * Map {@link List} of {@link Game} to {@link List} of {@link GameDTO}.
   *
   * @param games list of Game objects to map
   * @return list of GameDTO mapped
   */
  List<GameDTO> toDTO(List<Game> games);

  /**
   * Map {@link List} of {@link GameDTO} to {@link List} of {@link Game}.
   *
   * @param games list of object to map
   * @return list of object mapped
   */
  List<Game> fromDTO(List<GameDTO> games);
}

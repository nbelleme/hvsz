package io.nbelleme.hvsz.mapper;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class GameMapper {

  //TODO implemnts

  /**
   * Map {@link Game} to {@link GameDTO}.
   *
   * @param game game object to map
   * @return gameDTO mapped
   */
  public static GameDTO toDTO(Game game) {
    Objects.requireNonNull(game);
    return GameDTO.build()
                  .setId(game.getId())
                  .setConfig(game.getConfig())
                  .setStatus(game.getStatus());
  }

  /**
   * Unmap {@link GameDTO} to {@link Game}.
   *
   * @param gameDTO object to unmap
   * @return game unmapped
   */
  public static Game fromDTO(GameDTO gameDTO) {
    Objects.requireNonNull(gameDTO);
    return Game.build()
               .setId(gameDTO.getId());

  }

  /**
   * Map {@link List} of {@link Game} to {@link List} of {@link GameDTO}.
   *
   * @param games list of Game objects to map
   * @return list of GameDTO mapped
   */
  public static List<GameDTO> toDTO(List<Game> games) {
    Objects.requireNonNull(games);

    return games.stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
  }


  /**
   * Map {@link List} of {@link GameDTO} to {@link List} of {@link Game}.
   *
   * @param games list of object to map
   * @return list of object mapped
   */
  public static List<Game> fromDTO(List<GameDTO> games) {
    Objects.requireNonNull(games);

    return games.stream()
                .map(GameMapper::fromDTO)
                .collect(Collectors.toList());
  }

}

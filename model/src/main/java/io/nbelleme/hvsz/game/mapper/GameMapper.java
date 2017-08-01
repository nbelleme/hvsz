package io.nbelleme.hvsz.game.mapper;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;

import java.util.Objects;

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
                  .setConfig(game.getConfig());
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
}

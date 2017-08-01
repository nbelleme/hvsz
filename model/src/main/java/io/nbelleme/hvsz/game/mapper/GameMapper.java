package io.nbelleme.hvsz.game.mapper;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.transfer.GameDTO;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class GameMapper {

  //TODO implemnts

  public static GameDTO toDTO(Game game) {
    Objects.requireNonNull(game);
    return GameDTO.build()
                  .setId(game.getId())
                  .setConfig(game.getConfig());
  }

  public static Game fromDTO(GameDTO gameDTO) {
    Objects.requireNonNull(gameDTO);
    return Game.build()
               .setId(gameDTO.getId());

  }
}

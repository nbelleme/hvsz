package io.nbelleme.persistence.dpo.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.persistence.dpo.AbstractDPO;

public class GameDpo extends AbstractDPO<Game> {


  /**
   * Contructor.
   */
  public GameDpo() {
    super();
  }

  /**
   * Constructor.
   *
   * @param game game
   */
  public GameDpo(Game game) {
    super(game);
  }

  /**
   * Constructor.
   *
   * @param game       game
   * @param id         id
   * @param lastUpdate lastUpdate
   */
  @JsonCreator
  public GameDpo(@JsonProperty("dto") Game game,
                 @JsonProperty("_id") String id,
                 @JsonProperty("lastUpdate") Long lastUpdate) {
    super(id, lastUpdate, game);
  }


  public static GameDpo build(Game game) {
    return new GameDpo(game);

  }


}

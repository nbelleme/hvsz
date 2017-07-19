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
  @JsonCreator
  public GameDpo(@JsonProperty("game") Game game) {
    super(game);
  }

}

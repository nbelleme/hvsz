package io.nbelleme.persistence.dpo.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.persistence.dpo.AbstractDPO;

public class GameDpo extends AbstractDPO<Game> {


  public GameDpo() {
    super();
  }

  @JsonCreator
  public GameDpo(@JsonProperty("game") Game game) {
    super(game);
  }

}

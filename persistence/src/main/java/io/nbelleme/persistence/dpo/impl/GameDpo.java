package io.nbelleme.persistence.dpo.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.persistence.dpo.AbstractDPO;

@JsonIgnoreProperties(ignoreUnknown = true)
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
  private GameDpo(Game game) {
    super(game);
  }

  /**
   * Constructor.
   *
   * @param game       game
   * @param lastUpdate lastUpdate
   */
  @JsonCreator
  GameDpo(@JsonProperty("dto") Game game,
          @JsonProperty("lastUpdate") Long lastUpdate) {
    super(lastUpdate, game);


  }

  /**
   * Default constructor.
   * @param game game
   * @return GameDpo new GameDpo
   */
  public static GameDpo build(Game game) {
    return new GameDpo(game);
  }


}

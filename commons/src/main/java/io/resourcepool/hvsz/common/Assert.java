package io.resourcepool.hvsz.common;

import io.resourcepool.hvsz.common.exceptions.HumanIsDeadException;
import io.resourcepool.hvsz.common.exceptions.IllegalGameStateException;
import io.resourcepool.hvsz.common.exceptions.NoGameDefinedException;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.humans.Life;

/**
 * Business layer assertions.
 *
 * @author Loïc Ortola on 04/05/2017
 */
public abstract class Assert {

  /**
   * Asserts that the game is not null.
   *
   * @param game The game
   */
  public static void gameDefined(Game game) {
    if (game == null) {
      throw new NoGameDefinedException();
    }
  }


  /**
   * Asserts that the game is not null, and has a state Started or Paused.
   *
   * @param game The game
   */
  public static void gameOngoing(Game game) {
    gameDefined(game);
    if (!game.getStatus().isOngoing()) {
      throw new IllegalGameStateException();
    }
  }

  /**
   * Asserts that the game is not null, and has a state Started.
   *
   * @param game The game
   */

  public static void gameActive(Game game) {
    gameDefined(game);
    if (!game.getStatus().isActive()) {
      throw new IllegalGameStateException();
    }
  }

  /**
   * Asserts that the game is not null, and is over (either a new game not started yet, or a victory).
   *
   * @param game The game
   */

  public static void gameOver(Game game) {
    gameDefined(game);
    if (!game.getStatus().isOver()) {
      throw new IllegalGameStateException();
    }
  }

  /**
   * Asserts that the game is not null, and ready to start (same as game over).
   *
   * @param game The game
   */

  public static void gameReadyToStart(Game game) {
    gameDefined(game);
    if (game.getStatus().isOngoing()) {
      throw new IllegalGameStateException();
    }
  }

  /**
   * Asserts that the game is not null, and is paused.
   *
   * @param game The game
   */
  public static void gamePaused(Game game) {
    gameDefined(game);
    if (!game.getStatus().isPaused()) {
      throw new IllegalGameStateException();
    }
  }

  /**
   * Asserts that a human is not null and alive.
   *
   * @param life the human life
   */
  public static void humanAlive(Life life) {
    if (life == null || !life.isAlive()) {
      throw new HumanIsDeadException();
    }
  }

  /**
   * Asserts that a human is not null and alive.
   *
   * @param life the human life
   * @param zone zone that asked the check
   */
  public static void humanAlive(Life life, Object zone) {
    if (life == null || !life.isAlive()) {
      throw new HumanIsDeadException(zone);
    }
  }

}

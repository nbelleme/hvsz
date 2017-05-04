package io.resourcepool.hvsz.web;

import io.resourcepool.hvsz.common.exceptions.GameOngoingException;
import io.resourcepool.hvsz.common.exceptions.GameOverOrPausedException;
import io.resourcepool.hvsz.common.exceptions.HumanIsDeadException;
import io.resourcepool.hvsz.common.exceptions.NoGameDefinedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This handles MVC-level exception handling.
 *
 * @author Loïc Ortola on 04/05/2017
 */
@ControllerAdvice
public class ExceptionController {

  /**
   * @return .
   */
  @ExceptionHandler({GameOverOrPausedException.class})
  public String gameOver() {
    return "redirect:/game/dashboard?error=over-or-paused";
  }

  /**
   * @return .
   */
  @ExceptionHandler({HumanIsDeadException.class})
  public String humanAlreadyDead() {
    return "redirect:/game/dashboard?error=already-dead";
  }

  /**
   * @return .
   */
  @ExceptionHandler({NoGameDefinedException.class})
  public String noGameDefined() {
    return "redirect:/game/dashboard?error=no-game-defined";
  }

  /**
   * @return .
   */
  @ExceptionHandler({GameOngoingException.class})
  public String gameOngoing() {
    return "redirect:/game/dashboard?error=game-ongoing";
  }
}

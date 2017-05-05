package io.resourcepool.hvsz.controller.web;

import io.resourcepool.hvsz.common.exceptions.HumanIsDeadException;
import io.resourcepool.hvsz.common.exceptions.IllegalGameStateException;
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
  @ExceptionHandler({IllegalGameStateException.class})
  public String gameOver() {
    return "redirect:/game?error=over-or-paused";
  }

  /**
   * @return .
   */
  @ExceptionHandler({HumanIsDeadException.class})
  public String humanAlreadyDead() {
    return "redirect:/game?error=already-dead";
  }

  /**
   * @return .
   */
  @ExceptionHandler({NoGameDefinedException.class})
  public String noGameDefined() {
    return "redirect:/game?error=no-game-defined";
  }

}

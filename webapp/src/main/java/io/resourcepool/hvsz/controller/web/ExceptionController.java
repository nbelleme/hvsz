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

  // CHECKSTYLE_OFF
  /**
   * @param ex .
   * @return .
   */
  @ExceptionHandler({HumanIsDeadException.class})
  public String humanAlreadyDead(HumanIsDeadException ex) {
    if (ex.supplyZone != null) return "redirect:/food-supply/"+ex.supplyZone.getId();
    if (ex.safeZone != null) return "redirect:/safe-zone/"+ex.safeZone.getId();
    if (ex.zombieZone != null) return "redirect:/zombie-hq";

    return "redirect:/game?error=already-dead";
  }

  // CHECKSTYLE_ON

  /**
   * @return .
   */
  @ExceptionHandler({NoGameDefinedException.class})
  public String noGameDefined() {
    return "redirect:/game?error=no-game-defined";
  }

  /**
   * @return .
   */
  @ExceptionHandler({NumberFormatException.class})
  public String numberFormatException() {
    return "redirect:/game?error=wrong-input";
  }
}

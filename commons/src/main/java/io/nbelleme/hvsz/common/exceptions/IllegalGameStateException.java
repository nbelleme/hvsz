package io.nbelleme.hvsz.common.exceptions;

/**
 * This exception is thrown when an action was occured when the
 * game is on an illegal.
 *
 * @author Loïc Ortola on 03/05/2017
 */
public class IllegalGameStateException extends IllegalStateException {
  private static final long serialVersionUID = 101442697398023937L;

  public IllegalGameStateException() {
    super();
  }

  public IllegalGameStateException(String message) {
    super(message);
  }
}

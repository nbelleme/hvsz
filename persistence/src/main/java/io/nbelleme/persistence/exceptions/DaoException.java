package io.nbelleme.persistence.exceptions;

public class DaoException extends RuntimeException {

  private static final long serialVersionUID = -7967814117629993246L;

  /**
   * Constructor.
   *
   * @param message detailed message
   */
  public DaoException(String message) {
    super(message);
  }

  /**
   * Constructor.
   *
   * @param message   detailed message
   * @param throwable exception's cause
   */
  public DaoException(String message, Throwable throwable) {
    super(message, throwable);
  }

}

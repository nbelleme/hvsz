package io.nbelleme.hvsz.game;

public enum GameDifficulty {
  EASY(0.33),
  NORMAL(0.5),
  HARD(0.66);

  private double value;

  /**
   * Constructor.
   *
   * @param value value
   */
  GameDifficulty(double value) {
    this.value = value;
  }

  //CHECKSTYLE_OFF
  public double getValue() {
    return value;
  }
  //CHECKSTYLE_ON
}

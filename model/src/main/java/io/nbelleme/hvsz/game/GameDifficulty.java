package io.nbelleme.hvsz.game;

public enum GameDifficulty {
  EASY(0.33),
  NORMAL(0.5),
  HARD(0.66);

  private double value;

  GameDifficulty(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}

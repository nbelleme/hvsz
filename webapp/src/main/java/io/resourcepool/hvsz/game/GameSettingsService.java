package io.resourcepool.hvsz.game;

public interface GameSettingsService {

  /**
   * Set the game settings.
   *
   * @param gameSettings the game settings to set
   */
  void set(GameSettings gameSettings);

  /**
   * Return the game settings.
   *
   * @return current GameConfig
   */
  GameSettings get();
}
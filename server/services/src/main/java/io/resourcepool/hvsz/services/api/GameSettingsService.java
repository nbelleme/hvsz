package io.resourcepool.hvsz.services.api;

import io.resourcepool.hvsz.game.GameSettings;

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
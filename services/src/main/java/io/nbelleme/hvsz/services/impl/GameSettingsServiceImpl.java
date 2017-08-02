package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.GameSettingsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
final class GameSettingsServiceImpl implements GameSettingsService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  GameSettingsServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public void set(GameSettings gameSettings) {
  }

  @Override
  public GameSettings get() {
    return null;
  }
}

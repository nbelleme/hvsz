package io.resourcepool.hvsz.controller.impl;

import io.resourcepool.hvsz.controller.api.HumainRestController;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.services.api.HumanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/api/human")
final class HumainRestControllerImpl implements HumainRestController {
  private HumanService humanService;

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param humanService the humanService
   * @param gameService  the gameService
   */
  HumainRestControllerImpl(HumanService humanService, GameService gameService) {
    this.humanService = Objects.requireNonNull(humanService);
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  @PostMapping("/kill/{token}")
  @ResponseBody
  public Boolean kill(@PathVariable(value = "token") Integer token) {
    return humanService.kill(token);
  }

  @Override
  @PostMapping("/takeLife")
  public Life takeLife() {
    return humanService.spawn();
  }

  @Override
  @GetMapping("/{lifeToken}/nbResource")
  public int getNbResourceByLife(@PathVariable("lifeToken") int lifeToken) {
    return humanService.getLifeByToken(lifeToken).getNbResources();
  }

  @Override
  @GetMapping("/remaining")
  public int getRemainingLives() {
    return gameService.get().getStatus().getRemainingHumanTickets();
  }

  @Override
  @GetMapping("/active")
  public int getActiveLives() {
    return gameService.get().getStatus().getCurrentHumansOnField();
  }

}

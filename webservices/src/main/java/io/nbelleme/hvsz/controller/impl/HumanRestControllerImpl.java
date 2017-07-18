package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.HumanRestController;
import io.nbelleme.hvsz.humans.Human;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.HumanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/api/human")
final class HumanRestControllerImpl implements HumanRestController {
  private HumanService humanService;

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param humanService the humanService
   * @param gameService  the gameService
   */
  HumanRestControllerImpl(HumanService humanService, GameService gameService) {
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
  @PostMapping("/takeHuman")
  public Human takeHuman() {
    return humanService.spawn();
  }

  @Override
  @GetMapping("/{HumanToken}/nbResource")
  public int getNbResourceByHuman(@PathVariable("humanToken") int lifeToken) {
    return humanService.getHumanByToken(lifeToken).getNbResources();
  }

  @Override
  @GetMapping("/remaining")
  public int getRemainingLives() {
    return gameService.getCurrent().getStatus().getRemainingHumanTickets();
  }

  @Override
  @GetMapping("/active")
  public int getActiveLives() {
    return gameService.getCurrent().getStatus().getCurrentHumansOnField();
  }

}

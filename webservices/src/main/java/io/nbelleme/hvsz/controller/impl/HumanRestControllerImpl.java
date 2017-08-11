package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.HumanRestController;
import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;
import io.nbelleme.hvsz.mapper.api.GenericMapper;
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
  private GenericMapper<Human, HumanDTO> humanMapper;

  /**
   * Constructor.
   *
   * @param humanService the humanService
   * @param gameService  the gameService
   * @param humanMapper  humanMapper
   */
  HumanRestControllerImpl(HumanService humanService, GameService gameService, GenericMapper<Human, HumanDTO> humanMapper) {
    this.humanService = Objects.requireNonNull(humanService);
    this.gameService = Objects.requireNonNull(gameService);
    this.humanMapper = Objects.requireNonNull(humanMapper);
  }

  @Override
  @PostMapping("/kill/{token}")
  @ResponseBody
  public Boolean kill(@PathVariable(value = "token") Integer token) {
    return humanService.kill(token);
  }

  @Override
  @PostMapping("/new")
  public HumanDTO takeHuman() {
    Human human = humanService.spawn();
    return humanMapper.toDTO(human);
  }

  @Override
  @GetMapping("/{HumanToken}/nbResource")
  public int getNbResourceByHuman(@PathVariable("humanToken") int lifeToken) {
    return humanService.getHumanByToken(lifeToken).getNbResources();
  }

  @Override
  @GetMapping("/remaining")
  public int getRemainingLives() {
    return 0;
  }

  @Override
  @GetMapping("/active")
  public int getActiveLives() {
    return 0;
  }

}

package io.resourcepool.hvsz.controller.web;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameState;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.services.api.GameSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/game")
public class GameController {

  @Autowired
  private GameService gameService;

  @Autowired
  private GameSettingsService confService;


  /**
   * Get the dashboard page.
   *
   * @param model Model
   * @param error Error on other page
   * @return String (dashboard vue)
   */
  @GetMapping()
  public String dashboard(@RequestParam(required = false) String error, Model model) {
    Game active = gameService.get();
    if (error != null && !error.isEmpty()) {
      model.addAttribute("errorMessage", error);
    }
    if (active == null) {
      model.addAttribute("status", GameState.NONE);
      return "game/inactive";
    }
    if (active.getStatus().isOver()) {
      return "redirect:/game/over";
    }
    if (active.getStatus().isReadyToStart()) {
      model.addAttribute("status", active.getStatus().getGameState());
      return "game/inactive";
    }
    model.addAttribute("remainingTime", active.getStatus().getRemainingTime());
    model.addAttribute("humanTickets", active.getConfig().getHumanTickets());
    model.addAttribute("duration", active.getConfig().getGameDuration());
    model.addAttribute("humansOnField", active.getStatus().getCurrentHumansOnField());
    model.addAttribute("remainingHumanTickets", active.getStatus().getRemainingHumanTickets());
    model.addAttribute("safeZones", active.getSafeZones());
    model.addAttribute("foodSupplies", active.getFoodSupplies());
    model.addAttribute("status", active.getStatus().getGameState());
    model.addAttribute("statusName", active.getStatus().getGameState().name());
    return "game/dashboard";
  }

  /**
   * Return game over view.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @GetMapping("/over")
  public String gameOver(Model model) {
    Game game = gameService.get();
    Assert.gameOver(game);
    model.addAttribute("status", game.getStatus().getGameState().name());
    return "game/over";
  }

  /**
   * Start the game.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @GetMapping("/start")
  public String startGame(Model model) {
    Game game = gameService.get();
    Assert.gameReadyToStart(game);
    gameService.startGame();
    return "redirect:/game";
  }

  /**
   * Pause the game.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @GetMapping("/pause")
  public String pauseGame(Model model) {
    Game game = gameService.get();
    Assert.gameActive(game);
    gameService.pauseGame();
    return "redirect:/game";
  }

  /**
   * Resume the game.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @GetMapping("/resume")
  public String resumeGame(Model model) {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    gameService.resumeGame();
    return "redirect:/game";
  }

  /**
   * Stop the game.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @GetMapping("/stop")
  public String stopGame(Model model) {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    gameService.stopGame();
    return "redirect:/game";
  }
}

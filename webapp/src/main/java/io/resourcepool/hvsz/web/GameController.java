package io.resourcepool.hvsz.web;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.GameSettingsService;
import io.resourcepool.hvsz.game.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
   * @return String (dashboard vue)
   */
  @GetMapping("/")
  public String dashboard(Model model) {
    Game active = gameService.getActive();
    if (active == null) {
      model.addAttribute("status", GameState.NOT_STARTED);
      return "game/dashboard";
    }
    model.addAttribute("remainingTime", active.getStatus().getRemainingTime());
    model.addAttribute("humanTickets", active.getConfig().getHumanTickets());
    model.addAttribute("duration", active.getConfig().getGameDuration());
    model.addAttribute("humansOnField", active.getStatus().getCurrentHumansOnField());
    model.addAttribute("remainingHumanTickets", active.getStatus().getRemainingHumanTickets());
    model.addAttribute("safeZones", active.getSafeZones());
    model.addAttribute("foodSupplies", active.getFoodSupplies());
    model.addAttribute("status", active.getStatus().getGameState());
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
    Game game = gameService.getActive();
    Assert.gameOver(game);
    model.addAttribute("status", game.getStatus().getGameState());
    return "game/over";
  }

  /**
   * Start the game.
   *
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @PostMapping("/start")
  public String startGame(Model model) {
    Game game = gameService.getActive();
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
  @PostMapping("/pause")
  public String pauseGame(Model model) {
    Game game = gameService.getActive();
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
  @PostMapping("/resume")
  public String resumeGame(Model model) {
    Game game = gameService.getActive();
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
  @PostMapping("/stop")
  public String stopGame(Model model) {
    Game game = gameService.getActive();
    Assert.gameOngoing(game);
    gameService.stopGame();
    return "redirect:/game";
  }
}

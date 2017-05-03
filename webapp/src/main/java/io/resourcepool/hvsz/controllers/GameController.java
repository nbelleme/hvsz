package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.service.ConfigurationService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class GameController {

    @Autowired
    private ConfigurationService confService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ResourceService resourceService;

    /**
     * Start the game.
     *
     * @param model Model
     * @return String (redirect to dashboard)
     */
    @GetMapping("/game/start")
    public String startGame(Model model) {

        GameConfig conf = confService.get(1L);

        GameStatus status = GenericBuilder.of(GameStatus::new)
                .with(GameStatus::setHumanPlayers, conf.getNbHuman())
                .with(GameStatus::setZombiePlayers, conf.getNbZombie())
                .with(GameStatus::setNbHumanAlive, 0)
                .with(GameStatus::setTimeLeft, conf.getGameDuration()) 
                .with(GameStatus::setNbLifeLeft, conf.getNbSafezoneLifes())
                .with(GameStatus::setStarted, true)
                .build();

        statusService.add(status, 1L);

        ArrayList<SupplyZone> supplyZones = new ArrayList<>();
        int nbSupplyZones = conf.getNbSupplyZone();
        int nbSupplyResources = conf.getNbSupplyResources();
        for (int i = 0; i < nbSupplyZones; i++) {
            supplyZones.add(new SupplyZone(i, nbSupplyResources / nbSupplyZones));
        }
        resourceService.setSupplyZones(supplyZones);

        ArrayList<SafeZone> safeZones = new ArrayList<>();
        int nbSafeZones = conf.getNbSafezone();
        for (int i = 0; i < nbSafeZones; i++) {
            safeZones.add(new SafeZone(i, 25, 100));
        }
        resourceService.setSafeZones(safeZones);


        return "redirect:/dashboard";
    }

    /**
     * Pause the game.
     *
     * @param model Model
     * @return String (redirect to dashboard)
     */
    @GetMapping("/game/pause")
    public String pauseGame(Model model) {
        GameStatus gameStatus = statusService.get(1L);
        gameStatus.setGameState(GameStateEnum.PAUSED.name());
        statusService.add(gameStatus, 1L);
        return "redirect:/dashboard";
    }

    /**
     * Resume the game.
     *
     * @param model Model
     * @return String (redirect to dashboard)
     */
    @GetMapping("/game/resume")
    public String resumeGame(Model model) {
        GameStatus gameStatus = statusService.get(1L);
        gameStatus.setGameState(GameStateEnum.ONGOING.name());
        statusService.add(gameStatus, 1L);
        return "redirect:/dashboard";
    }

    /**
     * Stop the game.
     *
     * @param model Model
     * @return String (redirect to dashboard)
     */
    @GetMapping("/game/stop")
    public String stopGame(Model model) {
        GameStatus gameStatus = statusService.get(1L);
        gameStatus.setGameState(GameStateEnum.ZOMBIE_VICTORY.name());
        statusService.add(gameStatus, 1L);
        return "redirect:/dashboard";
    }

    /**
     * Return game over view.
     *
     * @param model Model
     * @return String (redirect to dashboard)
     */
    @GetMapping("/game/over")
    public String gameOver(Model model) {
        model.addAttribute("game_status", statusService.get(1L).getGameState());
        return "game-over";
    }
}

package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.service.ConfigurationServiceImpl;
import io.resourcepool.hvsz.service.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @Autowired
    private ConfigurationServiceImpl confService;
    @Autowired
    private StatusServiceImpl statusService;

    @GetMapping("/game/start")
    public String startGame(Model model) {

        GameConfig conf = confService.get(1L);

        GameStatus status = GenericBuilder.of(GameStatus::new)
                .with(GameStatus::setHumanPlayers, conf.getNbHuman())
                .with(GameStatus::setZombiePlayers, conf.getNbZombie())
                .with(GameStatus::setNbHumanAlive, conf.getNbHuman())
                .with(GameStatus::setTimeLeft, conf.getGameDuration())
                .with(GameStatus::setStarted, true)
                .build();

        statusService.add(status, 1L);

        return "redirect:/dashboard";
    }
}

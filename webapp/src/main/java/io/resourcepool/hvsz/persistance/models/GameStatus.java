package io.resourcepool.hvsz.persistance.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;

public class GameStatus implements Serializable {
    Integer humanPlayers;
    Integer zombiePlayers;
    Integer nbHumanAlive;
    Integer nbLifeLeft;
    Integer timeLeft; // in min
    Instant timestampStart;
    Boolean started = false;
    ArrayList<Life> lives = new ArrayList<>();
    String gameState = GameStateEnum.NOT_STARTED.name();

    public Integer getHumanPlayers() {
        return humanPlayers;
    }

    public void setHumanPlayers(Integer humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    public Integer getZombiePlayers() {
        return zombiePlayers;
    }

    public void setZombiePlayers(Integer zombiePlayers) {
        this.zombiePlayers = zombiePlayers;
    }

    public Integer getNbHumanAlive() {
        return nbHumanAlive;
    }

    public void setNbHumanAlive(Integer nbHumanAlive) {
        this.nbHumanAlive = nbHumanAlive;
    }

    public Integer getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Boolean getStarted() {
        return started;
    }

    public Instant getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(Instant timestampStart) {
        this.timestampStart = timestampStart;
    }


    /**
     * set started status, also set gameState to ongoing if true.
     * @param started .
     */
    public void setStarted(Boolean started) {
        this.started = started;
        if (this.started) {
            this.gameState = GameStateEnum.ONGOING.name();
            this.timestampStart = Instant.now();
        }
    }

    public Integer getNbLifeLeft() {
        return nbLifeLeft;
    }

    public void setNbLifeLeft(Integer nbLifeLeft) {
        this.nbLifeLeft = nbLifeLeft;
    }

    public ArrayList<Life> getLives() {
        return this.lives;
    }

    public void setLives(ArrayList<Life> lives) {
        this.lives = lives;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }


    /**
     * get life by id.
     * @param id .
     * @return .
     */
    public Life getLife(int id) {
        for (Life l : lives) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    /**
     * get life by id.
     * @param token .
     * @return .
     */
    public Life getLifeByToken(String token) {
        for (Life l : lives) {
            if (l.getToken().equals(token)) {
                return l;
            }
        }
        return null;
    }
}

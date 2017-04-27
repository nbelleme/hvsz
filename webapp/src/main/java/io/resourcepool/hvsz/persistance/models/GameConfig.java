package io.resourcepool.hvsz.persistance.models;


import java.io.Serializable;

public class GameConfig implements Serializable {

    //configurated values
    Integer gameDuration;
    Integer difficulty;
    Integer nbHuman;
    Integer nbZombie;
    Integer nbSafezone;
    Integer nbSafezoneLifes;
    Integer nbSupplyZone;
    Integer nbSupplyResources;

    //generated values with difficulty
    Integer safezoneDropRate;

    public GameConfig() {}

    public Integer getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Integer gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getNbHuman() {
        return nbHuman;
    }

    public void setNbHuman(Integer nbHuman) {
        this.nbHuman = nbHuman;
    }

    public Integer getNbZombie() {
        return nbZombie;
    }

    public void setNbZombie(Integer nbZombie) {
        this.nbZombie = nbZombie;
    }

    public Integer getNbSafezone() {
        return nbSafezone;
    }

    public void setNbSafezone(Integer nbSafezone) {
        this.nbSafezone = nbSafezone;
    }

    public Integer getNbSafezoneLifes() {
        return nbSafezoneLifes;
    }

    public void setNbSafezoneLifes(Integer nbSafezoneLifes) {
        this.nbSafezoneLifes = nbSafezoneLifes;
    }

    public Integer getNbSupplyZone() {
        return nbSupplyZone;
    }

    public void setNbSupplyZone(Integer nbSupplyZone) {
        this.nbSupplyZone = nbSupplyZone;
    }

    public Integer getNbSupplyResources() {
        return nbSupplyResources;
    }

    public void setNbSupplyResources(Integer nbSupplyResources) {
        this.nbSupplyResources = nbSupplyResources;
    }

    public Integer getSafezoneDropRate() {
        return safezoneDropRate;
    }

    public void setSafezoneDropRate(Integer safezoneDropRate) {
        this.safezoneDropRate = safezoneDropRate;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "gameDuration=" + gameDuration +
                ", difficulty=" + difficulty +
                ", nbHuman=" + nbHuman +
                ", nbZombie=" + nbZombie +
                ", nbSafezone=" + nbSafezone +
                ", nbSafezoneLifes=" + nbSafezoneLifes +
                ", nbSupplyZone=" + nbSupplyZone +
                ", nbSupplyResources=" + nbSupplyResources +
                ", safezoneDropRate=" + safezoneDropRate +
                '}';
    }
}

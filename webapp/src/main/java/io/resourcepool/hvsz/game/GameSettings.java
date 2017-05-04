package io.resourcepool.hvsz.game;


import java.io.Serializable;

public class GameSettings implements Serializable {

    Integer gameDuration;
    Integer difficulty;
    Integer humanTickets;
    Integer nbSafeZones;
    Integer nbFoodSupplyZones;
    Integer nbFoodSupplies;
    Integer maximumFoodTransfer;

    // Generated values with difficulty
    Integer safezoneDropRate;

    /**
     * GameConfig constructor.
     */
    public GameSettings() {
    }

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

    public Integer getHumanTickets() {
        return humanTickets;
    }

    public void setHumanTickets(Integer humanTickets) {
        this.humanTickets = humanTickets;
    }

    public Integer getNbSafeZones() {
        return nbSafeZones;
    }

    public void setNbSafeZones(Integer nbSafezone) {
        this.nbSafeZones = nbSafezone;
    }

    public Integer getNbFoodSupplyZones() {
        return nbFoodSupplyZones;
    }

    public void setNbFoodSupplyZones(Integer nbFoodSupplyZones) {
        this.nbFoodSupplyZones = nbFoodSupplyZones;
    }

    public Integer getNbFoodSupplies() {
        return nbFoodSupplies;
    }

    public void setNbFoodSupplies(Integer nbFoodSupplies) {
        this.nbFoodSupplies = nbFoodSupplies;
    }

    public Integer getSafezoneDropRate() {
        return safezoneDropRate;
    }

    public void setSafezoneDropRate(Integer safezoneDropRate) {
        this.safezoneDropRate = safezoneDropRate;
    }

    public Integer getMaximumFoodTransfer() {
        return maximumFoodTransfer;
    }

    public void setMaximumFoodTransfer(Integer maximumFoodTransfer) {
        this.maximumFoodTransfer = maximumFoodTransfer;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "gameDuration=" + gameDuration +
                ", difficulty=" + difficulty +
                ", humanTickets=" + humanTickets +
                ", nbSafeZones=" + nbSafeZones +
                ", nbFoodSupplyZones=" + nbFoodSupplyZones +
                ", nbFoodSupplies=" + nbFoodSupplies +
                ", safezoneDropRate=" + safezoneDropRate +
                ", maximumFoodTransfer=" + maximumFoodTransfer +
                '}';
    }
}

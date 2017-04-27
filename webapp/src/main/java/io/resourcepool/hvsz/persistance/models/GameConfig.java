package io.resourcepool.hvsz.persistance.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameConfig implements Serializable {
  Integer humansLives;
  Integer gameLength;
  Integer resourceDrainRate;
  Integer nbSafeZones;
  Integer nbSupplyZones;
  ArrayList<Integer> supplyZonesStorage = new ArrayList<>();
  public ArrayList<Integer> getSupplyZonesStorage() {
    return supplyZonesStorage;
  }

  public void setSupplyZonesStorage(ArrayList<Integer> supplyZonesStorage) {
    this.supplyZonesStorage = supplyZonesStorage;
  }

  public Integer getHumansLives() {
    return humansLives;
  }

  public void setHumansLives(Integer humansLives) {
    this.humansLives = humansLives;
  }

  public Integer getGameLength() {
    return gameLength;
  }

  public void setGameLength(Integer gameLength) {
    this.gameLength = gameLength;
  }

  public Integer getResourceDrainRate() {
    return resourceDrainRate;
  }

  public void setResourceDrainRate(Integer resourceDrainRate) {
    this.resourceDrainRate = resourceDrainRate;
  }

  public Integer getNbSafeZones() {
    return nbSafeZones;
  }

  public void setNbSafeZones(Integer nbSafeZones) {
    this.nbSafeZones = nbSafeZones;
  }

  public Integer getNbSupplyZones() {
    return nbSupplyZones;
  }

  public void setNbSupplyZones(Integer nbSupplyZones) {
    this.nbSupplyZones = nbSupplyZones;
  }


}

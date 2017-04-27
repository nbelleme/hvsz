package io.resourcepool.hvsz.persistance.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
  Long id = 0L; //uselss
  GameStatus status = new GameStatus();
  GameConfig config = new GameConfig();
  ArrayList<SafeZone> safeZones;
  ArrayList<SupplyZone> supplyZones;
  ArrayList<ZombieZone> zombieZones;

  public Game(){}
  public Game(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GameStatus getStatus() {
    return status;
  }

  public void setStatus(GameStatus status) {
    this.status = status;
  }

  public GameConfig getConfig() {
    return config;
  }

  public void setConfig(GameConfig config) {
    this.config = config;
    supplyZones = new ArrayList<>();
    int nbSupplyZones = config.getNbSupplyZone();
    int nbSupplyResources = config.getNbSupplyResources();
    for(int i =0; i<config.getNbSupplyZone(); i++){
      supplyZones.add(new SupplyZone(i, nbSupplyResources/nbSupplyZones));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    return getId().equals(game.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  public ArrayList<SupplyZone> getSupplyZones() {
    return this.supplyZones;
  }

  public void setSupplyZones(ArrayList<SupplyZone> supplyZones) {
    this.supplyZones = supplyZones;
  }
}

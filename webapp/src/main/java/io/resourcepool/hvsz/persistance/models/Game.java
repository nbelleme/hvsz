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

    public Game() {
    }

    public Game(Game g) {
        this.id = g.id;
        if (g.status != null) {
            this.status = g.status;
        }
        if (g.config != null) {
            this.config = g.config;
        }
        if (g.supplyZones != null) {
            this.supplyZones = g.supplyZones;
        } else {
            this.supplyZones = new ArrayList<>();
        }
        if (g.safeZones != null) {
            this.safeZones = g.safeZones;
        } else {
            this.safeZones = new ArrayList<>();
        }
        if (g.zombieZones != null) {
            this.zombieZones = g.zombieZones;
        } else {
            this.zombieZones = new ArrayList<>();
        }
    }

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
    }

    public ArrayList<SafeZone> getSafeZones() {
        return safeZones;
    }

    public void setSafeZones(ArrayList<SafeZone> safeZones) {
        this.safeZones = safeZones;
    }

    public ArrayList<SupplyZone> getSupplyZones() {
        return supplyZones;
    }

    public void setSupplyZones(ArrayList<SupplyZone> supplyZones) {
        this.supplyZones = supplyZones;
    }

    public ArrayList<ZombieZone> getZombieZones() {
        return zombieZones;
    }

    public void setZombieZones(ArrayList<ZombieZone> zombieZones) {
        this.zombieZones = zombieZones;
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
}

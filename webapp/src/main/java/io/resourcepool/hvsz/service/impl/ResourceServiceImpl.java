package io.resourcepool.hvsz.service.impl;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.ZoneResource;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private DaoMapDb dao;

  @Autowired
  private HumanService humanService;

  @Override
  public int get(SupplyZone supplyZone, int amount) {
    return supplyZone.getResource(amount);
  }

  @Override
  public int get(int idSupplyZone, int amount) {
    Game g = dao.get(1L);
    SupplyZone s = g.getSupplyZoneById(idSupplyZone);
    return get(s, amount);
  }

  @Override
  public int drop(SafeZone safeZone, int amount) {
    return safeZone.drop(amount);
  }

  @Override
  public int dropById(int safeZoneId, int amount, int id) {
    Game g = dao.get(1L);
    Life l = g.getStatus().getLife(id); //humanService.getLife(id);
    int dropped = l.dropResources(amount);
    SafeZone safeZone = getSafeZone(safeZoneId);
    dropped = safeZone.drop(dropped);
    dao.set(1L, g);
    return dropped;
  }

  @Override
  public int drop(int idSafeZone, int amount) {
    SafeZone s = getSafeZone(idSafeZone);
    return drop(s, amount);
  }

  @Override
  public void decreaseSafezones(int amount) {
      if (amount > 0) {
          ArrayList<SafeZone> safeZones = dao.get(1L).getSafeZones();
          for (SafeZone safeZone : safeZones) {
              drop(safeZone, amount);
          }
          Game g = dao.get(1L);
          g.setSafeZones(safeZones);
          dao.set(1L, g);
      }
  }

  @Override
  public SafeZone getSafeZone(String safeId) {
    return getSafeZone(Integer.parseInt(safeId));
  }

  @Override
  public SafeZone getSafeZone(int safeId) {
    return getAllSafeZone().get(safeId);
  }

  @Override
  public List<SafeZone> getAllSafeZone() {
    Game g = dao.get(1L);
    return g.getSafeZones();
  }

  @Override
  public SupplyZone getSupplyZone(int supplyId) {
    return getAllSupplyZone().get(supplyId);
  }

  @Override
  public List<SupplyZone> getAllSupplyZone() {
    Game g = dao.get(1L);
    return g.getSupplyZones();
  }

  @Override
  public List<ZoneResource> getAllZoneResource() {
    Game g = dao.get(1L);
    ArrayList<ZoneResource> zoneResources = new ArrayList<>(g.getSupplyZones());
    zoneResources.addAll(g.getSafeZones());
    return zoneResources;
  }

  /**
   * set supply zones.
   *
   * @param zones .
   */
  public void setSupplyZones(ArrayList<SupplyZone> zones) {
    Game g = dao.get(1L);
    g.setSupplyZones(zones);
    dao.set(1L, g);
  }

  /**
   * set safe zones.
   *
   * @param zones .
   */
  public void setSafeZones(ArrayList<SafeZone> zones) {
    Game g = dao.get(1L);
    g.setSafeZones(zones);
    dao.set(1L, g);
  }
}

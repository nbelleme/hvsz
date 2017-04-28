package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public int drop(SafeZone safeZone, int amount) {
        return safeZone.drop(amount);
    }

    @Override
    public int dropById(int safeZone, int amount, int id) {
        Game g = dao.get(1L);
        SafeZone s = g.getSafeZones().get(id);
        Life l = humanService.getLife(id);
        l.dropResources(amount);
        int dropped = s.drop(amount);
        dao.set(1L, g);
        return dropped;
    }

    @Override
    public void decreaseSafezones(int amount) {
        ArrayList<SafeZone> safeZones = dao.get(1L).getSafeZones();
        for (SafeZone safeZone : safeZones) {
            drop(safeZone, amount);
        }
        Game g = dao.get(1L);
        g.setSafeZones(safeZones);
        dao.set(1L, g);
    }

    @Override
    public SafeZone getSafeZone(int id) {
        Game g = dao.get(1L);
        return g.getSafeZones().get(id);
    }

    @Override
    public SupplyZone getSupplyZone(int id) {
        Game g = dao.get(1L);
        return g.getSupplyZones().get(id);
    }

    @Override
    public int getCurrentResource(int id) {
        return getSafeZone(id).getResource();
    }
}

package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public int get(SupplyZone supplyZone, int amount) {
        return supplyZone.getResource(amount);
    }

    @Override
    public int drop(SafeZone safeZone, int amount) {
        return safeZone.drop(amount);
    }

    public void decreaseSafezones(int amount) {
        ArrayList<SafeZone> safeZones = dao.get(1L).getSafeZones();
        for (SafeZone safeZone : safeZones) {
            drop(safeZone, amount);
        }
        Game g = dao.get(1L);
        g.setSafeZones(safeZones);
        dao.set(1L, g);
    }
}

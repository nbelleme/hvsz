package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for(SafeZone safezone : dao.get(1L).getSafeZones()) {
            drop(safezone, amount);
        }
    }
}

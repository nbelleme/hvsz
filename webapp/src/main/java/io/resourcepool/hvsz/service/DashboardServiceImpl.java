package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.Zone;
import io.resourcepool.hvsz.persistance.models.ZoneResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 27/04/17.
 */
public class DashboardServiceImpl implements DashboardService {
    @Override
    public int getLifeLeft() {
        return 56;
    }

    @Override
    public int getHuman() {
        return 20;
    }

    @Override
    public int getZombie() {
        return 5;
    }

    @Override
    public int getTime() {
        return 42;
    }

    @Override
    public List<ZoneResource> getZoneResource() {
        ArrayList<ZoneResource> listeZones = new ArrayList<>();
        listeZones.add(new SafeZone());
        listeZones.add(new SupplyZone());
        listeZones.add(new SafeZone());
        return listeZones;
    }
}

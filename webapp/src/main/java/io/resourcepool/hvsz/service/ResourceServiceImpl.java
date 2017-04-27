package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;

/**
 * Created by ebiz on 27/04/17.
 */
public class ResourceServiceImpl implements ResourceService {
    @Override
    public int get(SupplyZone supplyZone, int amount) {
        return supplyZone.getResource(amount);
    }

    @Override
    public int drop(SafeZone safeZone, int amount) {
        return safeZone.drop(amount);
    }
}

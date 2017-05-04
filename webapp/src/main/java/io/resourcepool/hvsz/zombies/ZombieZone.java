package io.resourcepool.hvsz.zombies;

import io.resourcepool.hvsz.common.models.Zone;

public class ZombieZone implements Zone {
    private String name = "Zombie Zone";

    @Override
    public String getName() {
        return name;
    }
}

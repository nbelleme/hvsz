package io.resourcepool.hvsz.persistance.models;


public class SupplyZone implements ZoneResource {
    private int resource = 2;
    private String name = "SupplyZone x";
    @Override
    public int getResource() {
        return resource;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getResource(int quantite) {
        resource -= quantite;
        if (resource < 0) {
            int over = -resource;
            resource = 0;
            return quantite - over;
        }
        return quantite;
    }
}

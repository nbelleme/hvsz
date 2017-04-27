package io.resourcepool.hvsz.persistance.models;

/**
 * Created by root on 26/04/17.
 */
public class SafeZone implements ZoneResource {
    private int resource = 5;
    private int maxResource = 10;
    private String name = "SafeZone x";

    @Override
    public int getResource() {
        return resource;
    }

    @Override
    public String getName() {
        return name;
    }

    public int drop(int quantite) {
        resource += quantite;
        if (resource > maxResource) {
            int over = resource - maxResource;
            resource = maxResource;
            return quantite - over;
        }
        return quantite;
    }
}

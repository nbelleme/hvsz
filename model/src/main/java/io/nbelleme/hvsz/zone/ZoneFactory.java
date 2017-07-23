package io.nbelleme.hvsz.zone;

import io.nbelleme.hvsz.game.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

public class ZoneFactory {


  public static List<SupplyZone> initSupplyZones(GameSettings conf) {
    Objects.requireNonNull(conf);

    int ngSupplyZone = conf.getNgSupplyZones();
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNgSupplyZones();

    List<io.nbelleme.hvsz.zone.SupplyZone> foodSupplies = new ArrayList<>(ngSupplyZone);


    LongStream.range(0, ngSupplyZone)
              .mapToObj(buildSupplyZone(foodPerZone))
              .forEach(foodSupplies::add);

    return foodSupplies;
  }


  public static List<SafeZone> initSafeZones(GameSettings conf) {
    Objects.requireNonNull(conf);

    int nbSafeZones = conf.getNbSafeZones();
    int defaultLevel = conf.getStartingSafeZoneSupplies();

    List<SafeZone> safeZones = new ArrayList<>(nbSafeZones);

    LongStream.range(0, nbSafeZones)
              .mapToObj(buildSafeZone(defaultLevel))
              .forEach(safeZones::add);

    return safeZones;
  }

  private static LongFunction<SafeZone> buildSafeZone(int defaultLevel) {
    return i -> SafeZone.build()
                        .setId(i)
                        .setLevel(defaultLevel);
  }

  private static LongFunction<SupplyZone> buildSupplyZone(int foodPerZone) {
    return i -> SupplyZone.build()
                          .setId(i)
                          .setCapacity(foodPerZone)
                          .setLevel(foodPerZone);
  }
}

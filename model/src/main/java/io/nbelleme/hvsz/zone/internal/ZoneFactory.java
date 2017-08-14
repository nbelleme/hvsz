package io.nbelleme.hvsz.zone.internal;

import io.nbelleme.hvsz.game.internal.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

public class ZoneFactory {


  /**
   * Init List of SupplyZones.
   *
   * @param conf conf
   * @return List of SupplyZones
   */
  public static List<SupplyZone> initSupplyZones(GameSettings conf) {
    Objects.requireNonNull(conf);

    int ngSupplyZone = conf.getNgSupplyZones();
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNgSupplyZones();

    List<SupplyZone> foodSupplies = new ArrayList<>(ngSupplyZone);

    LongStream.range(0, ngSupplyZone)
              .mapToObj(buildSupplyZone(foodPerZone))
              .forEach(foodSupplies::add);

    return foodSupplies;
  }

  /**
   * Init List of SafeZoneDTO.
   *
   * @param conf conf
   * @return List of SafeZoneDTO
   */
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

  /**
   * Fonctional interface building safeZone.
   *
   * @param defaultLevel defaultLevel
   * @return fonctional interface
   */
  private static LongFunction<SafeZone> buildSafeZone(int defaultLevel) {
    return i -> SafeZone.build()
                        .setLevel(defaultLevel)
                        .setCapacity(defaultLevel);
  }


  /**
   * Fonctional interface building safeZone.
   *
   * @param defaultLevel defaultLevel
   * @return fonctional interface
   */
  private static LongFunction<SupplyZone> buildSupplyZone(int defaultLevel) {
    return i -> SupplyZone.build()
                          .setLevel(defaultLevel)
                          .setCapacity(defaultLevel);
  }
}

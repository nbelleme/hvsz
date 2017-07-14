package io.nbelleme.hvsz.zone.api;

public interface ZoneResource extends Zone {

  /**
   * Get the zone resource.
   *
   * @return int
   */
  int getLevel();

  /**
   * Get the id of the zone.
   *
   * @return int
   */
  Integer getId();

  /**
   * Return the type of the ResourceZone.
   *
   * @return the type of the ResourceZone
   */
  String getType();
}

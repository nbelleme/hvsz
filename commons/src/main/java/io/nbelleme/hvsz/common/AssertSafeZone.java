package io.nbelleme.hvsz.common;

import io.nbelleme.hvsz.common.exceptions.ZoneDestroyedException;
import io.nbelleme.hvsz.zone.SafeZone;

import java.util.Objects;

public class AssertSafeZone {
  /**
   * Asserts that a safezone is not null and not destroyed.
   *
   * @param safeZone the human life
   */
  public static void zoneNotDestroyed(SafeZone safeZone) {
    Objects.requireNonNull(safeZone);
    if (!safeZone.isDestroyed()) {
      throw new ZoneDestroyedException();
    }
  }

}

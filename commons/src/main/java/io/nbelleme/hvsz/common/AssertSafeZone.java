package io.nbelleme.hvsz.common;

import io.nbelleme.hvsz.common.exceptions.ZoneDestroyedException;
import io.nbelleme.hvsz.zone.internal.SafeZone;

import java.util.Objects;

public abstract class AssertSafeZone {
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

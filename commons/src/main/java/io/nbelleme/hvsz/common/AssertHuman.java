package io.nbelleme.hvsz.common;

import io.nbelleme.hvsz.common.exceptions.HumanIsDeadException;
import io.nbelleme.hvsz.human.internal.Human;

public abstract class AssertHuman {
  /**
   * Asserts that a human is not null and alive.
   *
   * @param human the human life
   */
  public static void humanAlive(Human human) {
    if (human == null || !human.isAlive()) {
      throw new HumanIsDeadException();
    }
  }

  /**
   * Asserts that a human is not null and alive.
   *
   * @param human the human life
   * @param zone  zone that asked the check
   */
  public static void humanAlive(Human human, Object zone) {
    if (human == null || !human.isAlive()) {
      throw new HumanIsDeadException(zone);
    }
  }
}

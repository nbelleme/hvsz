#include "FastLED.h"

/**
 * Color Strategy:
 * SingleColor: Will draw all LEDS of the same color
 * ColorGradient: Will draw LEDS with a gradient until current level"
 */
enum ColorStrategy { SingleColor, ColorGradient };

/**
 * A Gauge represents either a vertical or horizontal gauge on the board
 */
class Gauge {
public:
  Gauge(byte ledCount);
  /**
   * Return the gauge % level
   */
  byte getLevel();
  /**
   * Set Gauge level in %
   */
  void setLevel(byte level);
  /**
   * Return the number of leds the gauge has
   */
  byte getLedCount();

  void setLedCount(byte count);
  /**
   * Return the color strategy
   */
  ColorStrategy getColorStrategy();
  /**
   * Set color strategy
   */
  void setColorStrategy(ColorStrategy str);
  /**
   * Return gauge color in function of specified level
   */
  CRGB computeColor(byte level);
  /**
   * Set your own color computation function (better than subclassing your own Gauge)
   */
  void overrideComputeColor(CRGB (*func)(byte));

private:
  byte ledCount;
  byte level;
  ColorStrategy colorStrategy;
  CRGB (*computeColorFunc) (byte);
};

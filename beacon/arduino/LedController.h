#include "FastLED.h"

#define NUM_LEDS 52
#define V_GAUGE_NUM_LEDS 14
#define H_GAUGE_NUM_LEDS 8

#ifndef BRIGHTNESS_MIN
#define BRIGHTNESS_MIN  60
#endif
#ifndef BRIGHTNESS_MAX
#define BRIGHTNESS_MAX  250
#endif
#ifndef BRIGHTNESS_INC
#define BRIGHTNESS_INC 7
#endif

#ifndef LED_PIN
#define LED_PIN 10
#endif

class Gauge;

/**
 * The Led Controller controls all the leds of the Beacon
 */
class LedController {
public:
  LedController();
  void begin();
  void update();
  void stop();
  /**
   * See enum declaration for details
   */
  byte getHorizontalLEDsStrategy();
  void setHorizontalLEDsStrategy(byte str);
  Gauge* getVGauge1();
  Gauge* getVGauge2();
  Gauge* getHGauge1();
  Gauge* getHGauge2();
  Gauge* getHGauge3();
private:
  Gauge* vGauge1;
  Gauge* vGauge2;
  Gauge* hGauge1;
  Gauge* hGauge2;
  Gauge* hGauge3;
  byte ledStrategy;
  CRGB leds[NUM_LEDS];
  byte bri;
  boolean dir;
  /**
   * Used internally to update the current gauge color
   */
  void updateGauge(byte offset, Gauge* gauge);
  /**
   * Used internally to determine the next gauge brightness
   */
  void nextBrightness();
};

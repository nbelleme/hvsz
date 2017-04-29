#include "LedController.h"
#include "FastLED.h"
#include <math.h>

/**
 * The Led Controller controls all the leds of the Beacon
 */
LedController::LedController() {
  this->vGauge1 = new Gauge(V_GAUGE_NUM_LEDS);
  this->vGauge2 = new Gauge(V_GAUGE_NUM_LEDS);
  this->hGauge1 = new Gauge(H_GAUGE_NUM_LEDS);
  this->hGauge2 = new Gauge(H_GAUGE_NUM_LEDS);
  this->hGauge3 = new Gauge(H_GAUGE_NUM_LEDS);
  this-> ledStrategy = HLEDsStrategy::Beacon;
  bri = 0;
  dir = true;
}

Gauge* LedController::getVGauge1() {
  return this->vGauge1;
}
Gauge* LedController::getVGauge2() {
  return this->vGauge2;
}
Gauge* LedController::getHGauge1() {
  return this->hGauge1;
}
Gauge* LedController::getHGauge2() {
  return this->hGauge2;
}
Gauge* LedController::getHGauge3() {
  return this->hGauge3;
}

HLEDsStrategy LedController::getHorizontalLEDsStrategy() {
  return this->ledStrategy;
}
void LedController::setHorizontalLEDsStrategy(HLEDsStrategy str) {
  this->ledStrategy = str;
}


void LedController::begin() {
  FastLED.addLeds<NEOPIXEL, LED_PIN>(this->leds, NUM_LEDS);
  FastLED.setBrightness(0);
  FastLED.clear(true);
}

void LedController::update() {
  updateGauge(0, vGauge1);
  updateGauge(14, vGauge2);
  switch (this->ledStrategy) {
    case Separate:
      // Separate <=> each line is a separate gauge
      updateGauge(28, hGauge1);
      updateGauge(36, hGauge2);
      updateGauge(44, hGauge3);
      break;
    case Combine:
      // Combine <=> hGauge1 is the model, the others are identical
      updateGauge(28, hGauge1);
      updateGauge(36, hGauge1);
      updateGauge(44, hGauge1);
      break;
    case Beacon:
      // TODO will implement cool animation
      updateGauge(28, hGauge1);
      updateGauge(36, hGauge2);
      updateGauge(44, hGauge3);
  }
  FastLED.setBrightness(bri);
  FastLED.show();
  nextBrightness();
}

void LedController::updateGauge(byte offset, Gauge* gauge) {
  byte ratio = floor(((double) 100) / gauge->getLedCount());
  byte level = (byte) (((double) gauge->getLevel()) / ratio);
  CRGB color = CRGB::Black;
  switch (gauge->getColorStrategy()) {
    case SingleColor:
      color = gauge->computeColor(gauge->getLevel());
      for (byte i = 0; i < level; i++) {
        leds[i + offset] = color;
      }
      break;
    case ColorGradient:
      for (byte i = 0; i < level; i++) {
        leds[i + offset] = gauge->computeColor(ratio * i);
      }
      break;
  }
}

void LedController::nextBrightness() {
    if (bri >= BRIGHTNESS_MAX) {
      dir = false;
    } else if (bri <= BRIGHTNESS_MIN) {
      dir = true;
    }
    dir ? bri+=BRIGHTNESS_INC : bri-=BRIGHTNESS_INC;
}

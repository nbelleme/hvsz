#include "HumansVsZombiesGame.h"
#include <math.h>
#define LED_PIN     10

Game game = HumansVsZombiesGame();
Gauge* gauge1;
void setup() {
  Serial.begin(9600);
  // Start Led Controller
  game.getLedController()->begin();

  // Set dummy values for testing
  gauge1 = game.getLedController()->getVGauge1();
  gauge1->setLevel(79);
  game.getLedController()->getVGauge2()->setLevel(49);
  game.getLedController()->getHGauge1()->setLevel(59);
  game.getLedController()->getHGauge2()->setLevel(69);
  game.getLedController()->getHGauge3()->setLevel(100);
}
void loop() {
    // Call in loop to update LEDs
    game.getLedController()->update();
    delay(30);
}


#include "HVsZGame.h"
#include "gauge.h"
#define LED_PIN 10

Game game = HVsZGame();
Gauge* gauge1;
void setup() {
  
  Serial.begin(9600);
  Serial.println("Init");
  //game.getMenuController()->begin();
  Serial.println("Done");
  // Start Led Controller
  game.getLedController()->begin();
  // Set dummy values for testing
  gauge1 = game.getLedController()->getVGauge1();
  gauge1->setLevel(79);
  game.getLedController()->getVGauge2()->setLevel(49);
  game.getLedController()->getHGauge1()->setLevel(59);
  game.getLedController()->getHGauge2()->setLevel(69);
  game.getLedController()->getHGauge3()->setLevel(100);
  Serial.println("Ok");
}
void loop() {
    // Call in loop to update LEDs
    game.getLedController()->update();
    game.getMenuController()->update();
    delay(30);
}



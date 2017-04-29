#include "HumansVsZombiesGame.h"

CRGB alwaysBlueColorCompute (byte level) { return CRGB::Blue; }

CRGB alwaysPurpleColorCompute (byte level) { return CRGB::Purple; }

HumansVsZombiesGame::HumansVsZombiesGame() {
  ledController = new LedController();
  ledController->getVGauge2()->setColorStrategy(ColorStrategy::SingleColor);
  ledController->getVGauge2()->overrideComputeColor(&alwaysBlueColorCompute);
  ledController->getHGauge1()->overrideComputeColor(&alwaysPurpleColorCompute);
  ledController->getHGauge2()->overrideComputeColor(&alwaysPurpleColorCompute);
  ledController->getHGauge3()->overrideComputeColor(&alwaysPurpleColorCompute);
}

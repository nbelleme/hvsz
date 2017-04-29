#include "LedController.h"

/**
 * The Game represents any kind of game
 */
class Game {
public:
  LedController* getLedController();
protected:
  LedController* ledController;
};

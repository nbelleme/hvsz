#include "LedController.h"
#include "MenuController.h"

/**
 * The Game represents any kind of game
 */
class Game {
public:
  Game();
  LedController* getLedController();
  MenuController* getMenuController();
protected:
  LedController* ledController;
  MenuController* menuController;
};

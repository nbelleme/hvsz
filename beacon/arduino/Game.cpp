#include "Game.h"

Game::Game() {
  ledController = new LedController();
  menuController = new MenuController();
}

LedController* Game::getLedController() {
  return this->ledController;
}

MenuController* Game::getMenuController() {
  return this->menuController;
}

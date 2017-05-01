#include "Game.h"
#include <math.h>
#include <Arduino.h>
#define HVSZ_MENU_SETTINGS 2
#define HVSZ_MENU_BEACONTYPE 3
#define HVSZ_MENU_REMOTEHOST 4
#define HVSZ_MENU_BT_HSF 5
#define HVSZ_MENU_BT_ZHQ 6
#define HVSZ_MENU_BT_FS 7

class MenuCallbacks;

/**
 * The Game represents any kind of game
 */
class HVsZGame : public Game, public MenuCallbacks {
public:
  HVsZGame();
  void onBtnPressed(Button& btn, byte pin);
  void onBtnReleased(Button& btn, byte pin);
  void onMenuItemSelected(byte code);
  void onMenuItemExit(byte code);
  void onMenuPageChanged(byte code);
private:
  byte beaconType;
  String remoteHost;
  int remainingTime;
  MenuPage* showBaseMenu();
  MenuPage* showSettingsMenu();
  MenuPage* showBeaconTypeSetupMenu();
};

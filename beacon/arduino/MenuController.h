#include "Button.h"
#include "MenuPage.h"
#include <LiquidCrystal_I2C.h>
#include "MenuCallbacks.h"

#define BTN_RIGHT_PIN 30
#define BTN_BOTTOM_PIN 31
#define BTN_LEFT_PIN 32
#define BTN_TOP_PIN 33
#define BTN_EXIT_PIN 34
#define BTN_ENTER_PIN 35

#define MAX_PAGES 6


/**
 * The Menu Controller controls the LCD Screen and all the beacon buttons
 */
class MenuController : public MenuCallbacks {
public:
  MenuController();
  void begin();
  void update();
  void setMenuContent(MenuPage pages[], byte count);
  void showPage(byte index);
  void setButtonOverride(boolean btnO);
  void setMenuCallbacks(MenuCallbacks& cb);
  void onBtnPressed(Button& btn, byte pin);
  void onBtnReleased(Button& btn, byte pin);
  void onMenuItemSelected(byte code);
  void onMenuItemExit(byte code);
  void onMenuPageChanged (byte code);
private:
  LiquidCrystal_I2C* lcd;
  Button* btnRight;
  Button* btnBottom;
  Button* btnLeft;
  Button* btnTop;
  Button* btnExit;
  Button* btnEnter;
  MenuPage pages[MAX_PAGES];
  byte pageCount;
  byte currentPage;
  MenuCallbacks* cb;
  boolean buttonOverride;
  void createBindings(Button* button, byte pin);
};

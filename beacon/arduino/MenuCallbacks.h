#include <Arduino.h>

class Button;

class MenuCallbacks {
public:
  MenuCallbacks();
  virtual void onBtnPressed(Button& btn, byte pin);
  virtual void onBtnReleased(Button& btn, byte pin);
  virtual void onMenuItemSelected(byte code);
  virtual void onMenuItemExit(byte code);
  virtual void onMenuPageChanged (byte code);
};

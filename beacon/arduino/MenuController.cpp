#include "MenuController.h"
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

void MenuController::onMenuItemSelected(byte code) {
}

void MenuController::onMenuItemExit(byte code) {
}

void MenuController::onMenuPageChanged(byte code) {
}

void MenuController::onBtnPressed(Button& button, byte btnPin) {
  if (this->buttonOverride) {
    this->cb->onBtnPressed(button, btnPin);
    return;
  }
  switch (btnPin) {
    case BTN_RIGHT_PIN:
      if (this->currentPage < this->pageCount - 1) {
        this->showPage(this->currentPage + 1);
      }
      break;
    case BTN_BOTTOM_PIN:
      Serial.println("Bottom Pressed");
      break;
    case BTN_LEFT_PIN:
      if (this->currentPage > 0) {
        this->showPage(this->currentPage - 1);
      }
      break;
    case BTN_TOP_PIN:
      Serial.println("Top Pressed");
      break;
    case BTN_EXIT_PIN:
      this->cb->onMenuItemExit(this->pages[this->currentPage].getCode());
      break;
    case BTN_ENTER_PIN:
      this->cb->onMenuItemSelected(this->pages[this->currentPage].getCode());
      break;
  }
}

void MenuController::onBtnReleased(Button& button, byte btnPin) {
  if (this->buttonOverride) {
    this->cb->onBtnReleased(button, btnPin);
    return;
  }
  if (btnPin == BTN_RIGHT_PIN) {
     Serial.println("Right Released");
  }
  if (btnPin == BTN_BOTTOM_PIN) {
     Serial.println("Bottom Released");
  }
  if (btnPin == BTN_LEFT_PIN) {
     Serial.println("Left Released");
  }
  if (btnPin == BTN_TOP_PIN) {
     Serial.println("Top Released");
  }
  if (btnPin == BTN_EXIT_PIN) {
     Serial.println("Exit Released");
  }
  if (btnPin == BTN_ENTER_PIN) {
     Serial.println("Enter Released");
  }
}

MenuController::MenuController() {
  // Set the LCD address to 0x27 for a 16 chars and 2 line display
  lcd = new LiquidCrystal_I2C(0x27, 16, 2);
  // Btns
  this->btnRight = new Button(BTN_RIGHT_PIN, true, false, 25);
  this->btnBottom = new Button(BTN_BOTTOM_PIN, true, false, 25);
  this->btnLeft = new Button(BTN_LEFT_PIN, true, false, 25);
  this->btnTop = new Button(BTN_TOP_PIN, true, false, 25);
  this->btnExit = new Button(BTN_EXIT_PIN, true, false, 25);
  this->btnEnter = new Button(BTN_ENTER_PIN, true, false, 25);
  this->cb = this;
}

void MenuController::begin() {
  lcd->begin();
	lcd->backlight();
  // Custom characters with their ascii index
  uint8_t previous[8] = {0x1, 0x3, 0x7, 0xf, 0x7, 0x3, 0x1};
  uint8_t next[8] = {0x10, 0x18, 0x1c, 0x1e, 0x1c, 0x18, 0x10};
  lcd->createChar(1, previous);
  lcd->createChar(2, next);
  showPage(0);
}

void MenuController::update() {
  this->btnRight->read();
  this->btnBottom->read();
  this->btnLeft->read();
  this->btnTop->read();
  this->btnExit->read();
  this->btnEnter->read();

  createBindings(this->btnRight, BTN_RIGHT_PIN);
  createBindings(this->btnBottom, BTN_BOTTOM_PIN);
  createBindings(this->btnLeft, BTN_LEFT_PIN);
  createBindings(this->btnTop, BTN_TOP_PIN);
  createBindings(this->btnExit, BTN_EXIT_PIN);
  createBindings(this->btnEnter, BTN_ENTER_PIN);
}

void MenuController::setMenuContent(MenuPage pages[], byte count) {
  if (count > MAX_PAGES) {
    count = MAX_PAGES;
  }
  this->pageCount = count;
  this->currentPage = 0;
  for (byte i = 0; i < count; i++) {
    this->pages[i] = pages[i];
  }
}

void MenuController::setButtonOverride(boolean ob) {
  this->buttonOverride = ob;
}

void MenuController::createBindings(Button* button, byte pin) {
  if (button->wasReleased()) {
    this->onBtnPressed(*button, pin);
  } else if (button->wasPressed()) {
    this->onBtnReleased(*button, pin);
  }
}

void MenuController::showPage(byte index) {
  if (this->pageCount > index) {
    lcd->clear();
    this->currentPage = index;
    // Go to First Line
    lcd->setCursor(1,0);
    lcd->print(this->pages[currentPage].getLine1());
    // Go to Second Line
    lcd->setCursor(1,1);
    lcd->print(this->pages[currentPage].getLine2());
    // Print Next and Previous
    if (index > 0) {
      lcd->setCursor(0,0);
      lcd->write(1); // ASCII value for previous
    }
    if (index < this->pageCount - 1) {
      lcd->setCursor(15,0);
      lcd->write(2); // ASCII value for next
    }
    this->onMenuPageChanged(this->pages[currentPage].getCode());
  }
}

void MenuController::setMenuCallbacks(MenuCallbacks& cb) {
  this->cb = &cb;
}

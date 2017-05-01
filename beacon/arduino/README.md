# Arduino Module
This folder contains the main source code for Arduino.

So far, here is the work that has been achieved:

 * Led Control is fully operational and configurable
 * Menu Control is almost finished. Maybe a couple bugs left, but it seems to be working.

Here are the next steps:
 * Finish menu control: especially what happens when settings are changed, + Global IP configuration
 * Implement ESP-8266 and MQTT Messaging once server is set. Connect to remote server, wait to receive message.
 * Implement Game start and global game logic in HumanBeaconGameController.
 * Implement other game logics for ZombieBeacon and FoodSupplyBeacon

Here is what has been forgotten for now:
 * NFC Communication
 * SD-Card persistence (for settings)
 * Speaker communication


TODO: add schematics, board specifics, configuration, power supply, external libraries etc...


## Environment
You need to install:  
 * the latest Arduino IDE
 * FastLed Library (tested with 3.1.3)
 * LiquidCrystal_I2C (tested with 1.1.0)
 * [Button Library](https://github.com/JChristensen/Button) (tested with 1.0)

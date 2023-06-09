
#include "src/Light.h"
#include "src/Bluetooth.h"
#include "src/Roll.h"
#include "src/MsgService.h"
#include "Arduino.h"

Bluetooth *bluetooth;
Light *light;
Roll *roll;

void setup() {
  //MsgServiceBT.init();
  MsgService.init();
  while (!Serial) {};
  bluetooth = new Bluetooth(2,3);
  light = new Light(9);
  roll = new Roll(6);
  bluetooth->attach(light);
  bluetooth->attach(roll);
  light->attach(bluetooth);
  roll->attach(bluetooth);
  MsgService.attach(light);
}

void loop() {
  if (MsgService.isMsgAvailable()) {
    Serial.println("messaggio arrivato!");
    Msg* msg = MsgService.receiveMsg();
    light->update(new Event<Msg>(EventSourceType::MSG_SERVICE, msg));
  }
  bluetooth->notify();
}
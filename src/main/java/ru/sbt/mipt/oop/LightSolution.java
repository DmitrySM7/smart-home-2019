package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightSolution implements EventHandler {

    private SensorEvent event;
    private SmartHome smartHome;

    public LightSolution(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent() {
        Action action = new DeviceAction((objectFirst, objectSeond) -> {
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                if (objectSeond instanceof Room) {
                    Room room = (Room) objectSeond;
                    if (objectFirst instanceof Light) {
                        Light light = (Light) objectFirst;
                        updateLightState(event, light, room);
                    }
                }
            }
            return null;
        });
        smartHome.execute(action);
    }

    private void updateLightState(SensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
//        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
//
//            new LightIterator(smartHome).handleFunction((light,room) -> {
//                if (event.getObjectId().equals(light.getId())) {
//                    if (event.getType() == LIGHT_ON) {
//                        light.setOn(true);
//                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
//                    } else {
//                        light.setOn(false);
//                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
//                    }
//                }
//                return null;
//            });
//        }
}

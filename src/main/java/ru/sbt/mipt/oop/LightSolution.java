package ru.sbt.mipt.oop;

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
        smartHome.execute(object -> {
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(newObject -> {
                        if (newObject instanceof Light) {
                            Light light = (Light) newObject;
                            updateLightState(event, light, room);
                        }
                    });
                }
            }
        });
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
}

package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightSolution implements EventHandler {

    private SmartHome smartHome;

    public LightSolution(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof LightSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(newObject -> {
                        if (newObject instanceof Light) {
                            Light light = (Light) newObject;
                            updateLightState((LightSensorEvent) event, light, room);
                        }
                    });
                }
            }
        });
    }

    private void updateLightState(LightSensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LightEventType.LIGHT_OFF) {
                light.setStatus(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setStatus(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}

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
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {

            new LightIterator(smartHome).handleFunction((light,room) -> {
                if (event.getObjectId().equals(light.getId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
                return null;
            });
        }
    }
}

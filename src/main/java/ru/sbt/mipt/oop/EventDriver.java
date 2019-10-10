package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventDriver {
    static void run(SensorEvent event, SmartHome smartHome) {
        while (event != null) {
            System.out.println("Got event: " + event);

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                Solutions.solutionLight(event, smartHome);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                Solutions.solutionDoor(event, smartHome);
            }
            event = SensorEvent.getNextSensorEvent();
        }
    }
}

package ru.sbt.mipt.oop;

public class MainFunction {
    public static SensorEvent processing(SensorEvent event, SmartHome smartHome) {
        System.out.println("Got event" + event);
        new LightSolution(event,smartHome).handleEvent();
        new DoorSolution(event,smartHome).handleEvent();
        new HallDoorSolution(event,smartHome).handleEvent();
        return SensorEvent.getNextSensorEvent();
    }
}

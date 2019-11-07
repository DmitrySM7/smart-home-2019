package ru.sbt.mipt.oop;


import java.util.ArrayList;
import java.util.Collection;

public class HandleEventCreator {
    private SensorEvent event;
    private SmartHome smartHome;

    public HandleEventCreator(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public Collection<EventHandler> handleEventCreate() {
        Collection<EventHandler> handleEvents = new ArrayList<>();
        handleEvents.add(new LightSolution(event,smartHome));
        handleEvents.add(new DoorSolution(event,smartHome));
        handleEvents.add(new HallDoorSolution(event,smartHome));
        return handleEvents;
    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.Collection;

public class StateHandler {
    private SensorEvent event;
    private SmartHome smartHome;
    private Alarm alarm;

    public StateHandler (SensorEvent event, SmartHome smartHome, Alarm alarm) {
        this.event = event;
        this.smartHome = smartHome;
        this.alarm = alarm;
    }
    public void stateHandle() {
        while (event != null) {
            Collection<EventHandler> eventHandlers = new HandleEventCreator(event, smartHome).handleEventCreate();
            new MainFunction(event, eventHandlers, alarm).processing();
            event = new NextSensorEvent().getNextSensorEvent();
        }
    }
}

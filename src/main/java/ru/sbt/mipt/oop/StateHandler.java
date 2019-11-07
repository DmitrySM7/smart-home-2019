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

    public StateHandler(SmartHome smartHome, Alarm alarm) {
        this.smartHome = smartHome;
        this.alarm = alarm;
    }

    public void stateHandle() {
        while (event != null) {
            Collection<EventHandler> eventHandlers = new HandleEventCreator(event, smartHome).handleEventCreate();
            event = MainFunction.processing(event,eventHandlers, alarm);
        }
    }
}

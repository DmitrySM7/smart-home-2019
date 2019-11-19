package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.Collection;

public class MainFunction {

    private SensorEvent event;
    private Collection<EventHandler> eventHandlers;
    private Alarm alarm;

    public MainFunction(SensorEvent event, Collection<EventHandler> eventHandlers, Alarm alarm) {
        this.event = event;
        this.eventHandlers = eventHandlers;
        this.alarm = alarm;
    }

    public void processing() {
        System.out.println("Got event: " + event);
        for (EventHandler eventHandler : eventHandlers) {
            new EventDecorator(eventHandler, alarm).handleEvent();
        }
    }
}

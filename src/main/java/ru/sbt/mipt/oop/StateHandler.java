package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.Collection;

public class StateHandler {
    private EventDecorator decorator;

    public StateHandler(EventDecorator decorator) {
        this.decorator = decorator;
    }

    public void stateHandle() {
        SensorEvent event = new NextSensorEvent().getNextSensorEvent();
        while (event != null) {
            decorator.handleEvent(event);
            event = new NextSensorEvent().getNextSensorEvent();
        }
    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.alarm.AlarmType;

import java.util.Collection;

public class MainFunction {
    public static SensorEvent processing(SensorEvent event, Collection<EventHandler> eventHandlers, Alarm alarm) {
        System.out.println("Got event" + event);
        for (EventHandler eventHandler : eventHandlers) {
            if (alarm.getClassAvailability() == AlarmType.OFF) {
                eventHandler.handleEvent();
            } else if (alarm.getClassAvailability() == AlarmType.ALARM) {
                System.out.println("Alarm");
            } else {
                alarm.changeState(new AlarmState(), alarm.getCode());
            }
            if (alarm.getClassAvailability() != AlarmType.ALARM) {
                alarm.changeState(new ActivationState(), "Qwerty123");
            }
        }
        return SensorEvent.getNextSensorEvent();
    }
}

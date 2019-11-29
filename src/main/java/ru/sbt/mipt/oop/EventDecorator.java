package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationState;

public class EventDecorator implements EventHandler {

    private EventHandler eventHandler;
    private Alarm alarm;

    public EventDecorator(EventHandler eventHandler, Alarm alarm) {
        this.eventHandler = eventHandler;
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (alarm.getState() instanceof DeactivationState) {
            eventHandler.handleEvent(event);
            alarm.activate("Qwerty");
        } else if (alarm.getState() instanceof ActivationState) {
            alarm.danger();
            System.out.println("Sending sms");
        } else {
            System.out.println("Sending sms");
        }
    }
}
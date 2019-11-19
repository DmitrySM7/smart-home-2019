package ru.sbt.mipt.oop.alarm;

public class DangerState implements AlarmState {

    private Alarm alarm;

    public DangerState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivationState(alarm));
        }
    }

    @Override
    public void danger() {
    }
}

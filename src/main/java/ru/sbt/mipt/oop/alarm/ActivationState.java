package ru.sbt.mipt.oop.alarm;

public class ActivationState implements AlarmState {

    private Alarm alarm;

    public ActivationState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivationState(alarm));
        } else {
            alarm.changeState(new DangerState(alarm));
        }
    }

    @Override
    public void danger() {
        //экстренная кнопка
        alarm.changeState(new DangerState(alarm));
    }
}

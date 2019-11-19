package ru.sbt.mipt.oop.alarm;

public class DeactivationState implements AlarmState {

    private Alarm alarm;

    public DeactivationState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.changeState(new ActivationState(alarm));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void danger() {
        //экстренная кнопка
        alarm.changeState(new DangerState(alarm));
    }
}

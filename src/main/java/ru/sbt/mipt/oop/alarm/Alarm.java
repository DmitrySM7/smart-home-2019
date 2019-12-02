package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {

    private AlarmState state;
    private String code;

    public Alarm(String code) {
        this.state = new DeactivationState(this);
        this.code = code;
    }
    public void changeState(AlarmState state) {
        this.state = state;
    }

    public String getCode() { return code; }

    public void setCode(String code) {this.code = code;}

    public AlarmState getState(){return state;}

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void danger() {
        state.danger();
    }
}

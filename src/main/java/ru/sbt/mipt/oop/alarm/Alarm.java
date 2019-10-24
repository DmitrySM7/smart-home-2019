package ru.sbt.mipt.oop.alarm;

public class Alarm implements State {

    private State state;
    private String code;

    public Alarm(State state, String code) {
        this.state = state;
        this.code = code;
    }
    public void changeState(State state, String code) {
        AlarmType curType = getType();
        this.state = state;
        this.state = act(this.code.equals(code), curType);
    }

    @Override
    public State act(boolean status, AlarmType type) {
        return state.act(status, type);
    }

    @Override
    public AlarmType getClassAvailability() {
        return new AlarmStateDecorator(state).getClassAvailability();
    }


    public AlarmType getType() {
        if (this.state instanceof ActivationState) {
            return AlarmType.ON;
        } else if(this.state instanceof DeactivationState) {
            return AlarmType.OFF;
        } else {
            return AlarmType.ALARM;
        }
    }

    public String getCode() { return code; }
}

package ru.sbt.mipt.oop;

import java.util.function.Function;

public class Light implements Actionable {

    private final String id;
    private boolean status;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.status = isOn;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
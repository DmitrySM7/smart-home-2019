package ru.sbt.mipt.oop;

public class Door implements Actionable {
    private final String id;
    // this field show current status of the door (closed/open)
    private boolean status;

    public Door(boolean status, String id) {
        this.status = status;
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}

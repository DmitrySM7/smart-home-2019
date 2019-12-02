package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;

public class HomeRC implements RemoteControl {

    private HashMap<String, Command> remoteControls;

    public HomeRC() {
        this.remoteControls = new HashMap<>();
    }


    @Override
    public void onButtonPressed(String buttonCode) {
        if (remoteControls.containsKey(buttonCode)) {
            remoteControls.get(buttonCode).execute();
        }
    }

    public void addRemoteControl(String button, Command command) {
        remoteControls.put(button, command);
    }
}

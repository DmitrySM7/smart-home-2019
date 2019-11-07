package ru.sbt.mipt.oop.Remote_Control;

import java.util.HashMap;

public class RemoteControlRegistry {
    HashMap<String,RemoteControl> homeRC_map = new HashMap<>();

    /**
     * Register remote control with id rcId.
     * When button on a real remote control device is pressed this library will call remoteControl.onButtonPressed(...).
     * @param remoteControl
     * @param rcId
     */

    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        homeRC_map.put(rcId,remoteControl);
    }

}
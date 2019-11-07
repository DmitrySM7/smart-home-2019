package ru.sbt.mipt.oop.Remote_Control;

import java.util.HashMap;

public class HomeRC implements RemoteControl {
    //private String buttonCode;
    private String rcId;
    private HashMap<String, Command> map = new HashMap<>();

    public HomeRC(String buttonCode, String rcId) {
        //this.buttonCode = buttonCode;
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (rcId.equals(this.rcId)&& map.containsKey(buttonCode)) {
            map.get(buttonCode).doCommand();
        }
    }

    public void addCommand(Command command, String rcId) {
        map.put(rcId,command);
    }
}

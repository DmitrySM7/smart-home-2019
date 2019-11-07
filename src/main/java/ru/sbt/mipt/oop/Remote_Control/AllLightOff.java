package ru.sbt.mipt.oop.Remote_Control;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class AllLightOff implements Command {
    private SmartHome smarthome;

    public AllLightOff(SmartHome smarthome) {
        this.smarthome = smarthome;
    }


    @Override
    public void doCommand() {
        smarthome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                //light.setOn(true);
            }
        });
    }
}

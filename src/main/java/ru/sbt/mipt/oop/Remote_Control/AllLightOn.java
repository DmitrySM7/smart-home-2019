package ru.sbt.mipt.oop.Remote_Control;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class AllLightOn implements Command {
    private SmartHome smarthome;

    public AllLightOn(SmartHome smarthome) {
        this.smarthome = smarthome;
    }


    @Override
    public void doCommand() {
        smarthome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
    }
}

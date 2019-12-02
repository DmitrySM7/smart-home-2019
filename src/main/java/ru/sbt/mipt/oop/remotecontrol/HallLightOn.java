package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class HallLightOn implements Command {

    private SmartHome smartHome;

    public HallLightOn(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                        if (object_new instanceof Light) {
                            Light light = (Light) object_new;
                            light.setStatus(true);
                        }
                    });
                }
            }
        });
    }
}

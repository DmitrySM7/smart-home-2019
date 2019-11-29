package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorClosed implements Command {

    private SmartHome smartHome;

    public HallDoorClosed(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                                Door door = (Door) object_new;
                                door.setStatus(false);
                            }
                    );
                }
            }
        });
    }
}

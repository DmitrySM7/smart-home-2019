package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorSolution implements EventHandler {
    private SensorEvent event;
    private SmartHome smartHome;

    public DoorSolution(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent() {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {

            new DoorIterator(smartHome).handleFunction((door,room) -> {

                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
                return null;
            });
        }
    }
}

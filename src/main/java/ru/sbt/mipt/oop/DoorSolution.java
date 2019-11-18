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
        smartHome.execute(object -> {
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(newObject -> {
                        if (newObject instanceof Door) {
                            Door door = (Door) newObject;
                            updateDoorState(event, door, room);
                        }
                    });
                }

            }
        });
    }
    private void updateDoorState (SensorEvent event, Door door, Room room){
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
    }
}

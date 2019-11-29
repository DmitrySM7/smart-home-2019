package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorSolution implements EventHandler {
    private SmartHome smartHome;

    public DoorSolution(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof DoorSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(newObject -> {
                        if (newObject instanceof Door) {
                            Door door = (Door) newObject;
                            updateDoorState((DoorSensorEvent) event, door, room);
                        }
                    });
                }

            }
        });
    }
    private void updateDoorState (DoorSensorEvent event, Door door, Room room){
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DoorEventType.DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
    }
}

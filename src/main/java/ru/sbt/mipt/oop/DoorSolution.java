package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;

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
        Action action = new DeviceAction((objectFirst, objectSecond) -> {
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                if (objectSecond instanceof Room) {
                    Room room = (Room) objectSecond;
                    if (objectFirst instanceof Door) {
                        Door door = (Door) objectFirst;
                        updateDoorState(event, door, room);
                    }
                }

            }
            return null;
        });
        smartHome.execute(action);
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

//        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
//
//            new DoorIterator(smartHome).handleFunction((door, room) -> {
//
//                if (door.getId().equals(event.getObjectId())) {
//                    if (event.getType() == DOOR_OPEN) {
//                        door.setOpen(true);
//                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
//                    } else {
//                        door.setOpen(false);
//                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
//                    }
//                }
//                return null;
//            });
//        }
//    }
}

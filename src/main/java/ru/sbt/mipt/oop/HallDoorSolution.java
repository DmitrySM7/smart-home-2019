package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorSolution implements EventHandler {
    private SensorEvent event;
    private SmartHome smartHome;

    public HallDoorSolution(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)

    @Override
    public void handleEvent() {
        Action action = new DeviceAction((objectFirst, objectSecond) -> {
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                if (objectFirst instanceof Door) {
                    Door door = (Door) objectFirst;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_CLOSED) {
                            if (objectSecond instanceof Room) {
                                Room room = (Room) objectSecond;
                                if (room.getName().equals("hall")) {
                                    Action newAction = new DeviceAction((objectFirst_, objectSecond_) -> {
                                        if (objectFirst_ instanceof Light) {
                                            Light light = (Light) objectFirst_;
                                            light.setOn(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            Sender.sendCommand(command);
                                        }
                                        return null;
                                    });
                                    room.execute(newAction);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        });
        smartHome.execute(action);
    }

//        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
//
//            new DoorIterator(smartHome).handleFunction((door,room) -> {
//
//                if (door.getId().equals(event.getObjectId())) {
//                    if (room.getName().equals("hall")) {
//                        new LightIterator(smartHome).handleFunction((light,room_) -> {
//                            light.setOn(false);
//                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
//                            Sender.sendCommand(command);
//                            return null;
//                        });
//                    }
//                }
//                return null;
//            });
//        }
}

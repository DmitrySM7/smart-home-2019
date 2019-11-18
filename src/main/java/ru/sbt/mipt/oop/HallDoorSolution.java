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
        smartHome.execute(object -> {
            if (event.getType() == DOOR_CLOSED) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    if (room.getName().equals("hall")) {
                        room.execute((newObject) -> {
                            if (newObject instanceof Door) {
                                Door door = (Door) newObject;
                                if (door.getId().equals(event.getObjectId())) {
                                    room.execute(newObject_light -> {
                                        if (newObject_light instanceof Light) {
                                            Light light = (Light) newObject_light;
                                            light.setOn(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            System.out.println("Pretent we're sending command " + command);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}

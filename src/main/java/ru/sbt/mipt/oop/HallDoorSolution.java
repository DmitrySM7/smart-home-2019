package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorSolution implements EventHandler {
    private SmartHome smartHome;

    public HallDoorSolution(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)

    @Override
    public void handleEvent(SensorEvent event) {
        smartHome.execute((object) -> {
            if (event instanceof DoorSensorEvent) {
                if (((DoorSensorEvent) event).getType() == DoorEventType.DOOR_CLOSED) {
                    if (object instanceof Room) {
                        Room room = (Room) object;
                        if (room.getName().equals("hall")) {
                            room.execute((object_new) -> UpdateDoorState(room, object_new, event));
                        }
                    }
                }
            }
        });
    }

    private void UpdateDoorState(Room room, Actionable object_new, SensorEvent event) {
        if (object_new instanceof Door) {
            Door door = (Door) object_new;
            if (door.getId().equals(((DoorSensorEvent)event).getObjectId())) {
                room.execute(this::UpdateLightState);
            }
        }
    }

    private void UpdateLightState(Actionable object_new_light) {
        if (object_new_light instanceof Light) {
            Light light = (Light) object_new_light;
            light.setStatus(false);
            SensorCommand command = new SensorCommand(SensorEventType.LIGHT_OFF, light.getId());
            System.out.println("Pretent we're sending command " + command);
        }
    }
}

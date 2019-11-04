package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.alarm.Alarm;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AdapterEventHandler extends StateHandler {
    SensorEventsManager sensorEventsManager = new SensorEventsManager();
    SensorEvent sensorEvent;

    public AdapterEventHandler(SensorEvent event, SmartHome smartHome, Alarm alarm) {
        super(event, smartHome, alarm);
    }

    public AdapterEventHandler(SmartHome smartHome, Alarm alarm) {
        super(smartHome, alarm);
    }

    public void stateHandle() {
        sensorEventsManager.registerEventHandler(event -> System.out.println(toSensorEvent(event).toString()));
        sensorEventsManager.start();
    }

    private SensorEvent toSensorEvent(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                sensorEvent = new SensorEvent(LIGHT_ON, event.getObjectId());
                break;
            case "LightIsOff":
                sensorEvent = new SensorEvent(LIGHT_OFF, event.getObjectId());
                break;
            case "DoorIsOpen":
                sensorEvent = new SensorEvent(DOOR_OPEN, event.getObjectId());
                break;
            case "DoorIsClosed":
                sensorEvent = new SensorEvent(DOOR_CLOSED, event.getObjectId());
                break;
            case "DoorIsLocked":
                sensorEvent = new SensorEvent(DOOR_CLOSED, event.getObjectId());
                break;
            case "DoorIsUnlocked":
                sensorEvent = new SensorEvent(DOOR_OPEN, event.getObjectId());
                break;
        }
        return sensorEvent;
    }
}
package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.Adapters.Adapter;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.StateHandler;
import ru.sbt.mipt.oop.alarm.Alarm;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AdapterEventHandler extends StateHandler {
    private SensorEventsManager sensorEventsManager = new SensorEventsManager();
    private SmartHome smartHome;
    private Alarm alarm;
    private SensorEvent sensorEvent;

    public AdapterEventHandler(SmartHome smartHome, Alarm alarm) {
        super(smartHome, alarm);
        this.smartHome = smartHome;
        this.alarm = alarm;
    }

    public void stateHandle() {
        sensorEventsManager.registerEventHandler(event -> new Adapter(smartHome, alarm, this).handleEvent(event));
        sensorEventsManager.start();
    }


    public SensorEvent toSensorEvent(CCSensorEvent event) {
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
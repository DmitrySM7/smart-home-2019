package ru.sbt.mipt.oop.adapter;

import coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.DoorEventType;
import ru.sbt.mipt.oop.DoorSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public class DoorIsClosedConverter implements CCSensorEventConverter {

    private CCSensorEventConverter ccSensorEventConverter;

    public DoorIsClosedConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("DoorIsClosed")) || (ccSensorEvent.getEventType().equals("DoorIsLocked"))) {
            return new DoorSensorEvent(DoorEventType.DOOR_CLOSED, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}

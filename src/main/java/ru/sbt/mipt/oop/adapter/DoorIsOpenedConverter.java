package ru.sbt.mipt.oop.adapter;

import coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.DoorEventType;
import ru.sbt.mipt.oop.DoorSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public class DoorIsOpenedConverter implements CCSensorEventConverter {

    private CCSensorEventConverter ccSensorEventConverter;

    public DoorIsOpenedConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("DoorIsOpen")) || (ccSensorEvent.getEventType().equals("DoorIsUnlocked"))) {
            return new DoorSensorEvent(DoorEventType.DOOR_OPEN, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}

package ru.sbt.mipt.oop.adapter;

import coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.LightEventType;
import ru.sbt.mipt.oop.LightSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public class LightsIsOnConverter implements CCSensorEventConverter {

    private CCSensorEventConverter ccSensorEventConverter;

    public LightsIsOnConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("LightIsOn"))) {
            return new LightSensorEvent(LightEventType.LIGHT_ON, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}

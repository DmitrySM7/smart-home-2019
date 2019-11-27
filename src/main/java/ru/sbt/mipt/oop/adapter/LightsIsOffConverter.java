package ru.sbt.mipt.oop.adapter;

import coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.LightEventType;
import ru.sbt.mipt.oop.LightSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public class LightsIsOffConverter implements CCSensorEventConverter {

    private CCSensorEventConverter ccSensorEventConverter;

    public LightsIsOffConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("LightIsOff"))) {
            return new LightSensorEvent(LightEventType.LIGHT_OFF, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}

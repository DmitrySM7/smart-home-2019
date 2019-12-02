package ru.sbt.mipt.oop.adapter;

import coolcompany.smarthome.events.CCSensorEvent;
import coolcompany.smarthome.events.EventHandler;

public class AdapterEventHandler implements EventHandler {

    private ru.sbt.mipt.oop.EventHandler eventHandler;
    private CCSensorEventConverter ccSensorEventConverter;

    public AdapterEventHandler(ru.sbt.mipt.oop.EventHandler eventHandler, CCSensorEventConverter ccSensorEventConverter) {
        this.eventHandler = eventHandler;
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(ccSensorEventConverter.convert(event));
    }
}

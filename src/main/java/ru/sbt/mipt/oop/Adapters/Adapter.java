package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.StateHandler;
import ru.sbt.mipt.oop.alarm.Alarm;

public class Adapter implements EventHandler {

    private SmartHome smartHome;
    private Alarm alarm;
    private AdapterEventHandler adapterEventHandler;

    public Adapter(SmartHome smartHome, Alarm alarm, AdapterEventHandler adapterEventHandler) {
        this.smartHome = smartHome;
        this.alarm = alarm;
        this.adapterEventHandler = adapterEventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        new StateHandler(adapterEventHandler.toSensorEvent(event), smartHome, alarm);
    }
}

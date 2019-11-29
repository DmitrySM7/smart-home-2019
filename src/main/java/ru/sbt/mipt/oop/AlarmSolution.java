package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import static ru.sbt.mipt.oop.AlarmEventType.ALARM_ACTIVATE;

public class AlarmSolution implements EventHandler {

    private Alarm alarm;

    public AlarmSolution(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof AlarmSensorEvent) {
            if (((AlarmSensorEvent) event).getType() == ALARM_ACTIVATE) {
                alarm.activate(((AlarmSensorEvent) event).getCode());
            }
            else {
                alarm.deactivate(((AlarmSensorEvent) event).getCode());
            }
        }
    }
}

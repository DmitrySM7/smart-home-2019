package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmType;
import ru.sbt.mipt.oop.alarm.DeactivationState;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = FileReader.readHomeFile();
        // начинаем цикл обработки событий
        SensorEvent event = SensorEvent.getNextSensorEvent();
        Alarm alarm = new Alarm(new DeactivationState(), "Qwerty123");
        new StateHandler(event, smartHome, alarm).stateHandle();
    }
}

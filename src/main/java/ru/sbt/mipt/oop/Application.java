package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationState;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = FileReader.readHomeFile();
        // начинаем цикл обработки событий
        SensorEvent event = new NextSensorEvent().getNextSensorEvent();
        new StateHandler(event, smartHome, new Alarm("Qwerty123")).stateHandle();
    }
}
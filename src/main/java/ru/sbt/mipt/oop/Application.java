package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = FileReader.readHomeFile();
        // начинаем цикл обработки событий
        SensorEvent event = new NextSensorEvent().getNextSensorEvent();

        new StateHandler(event, smartHome, new Alarm("Qwerty123")).stateHandle();
    }
}
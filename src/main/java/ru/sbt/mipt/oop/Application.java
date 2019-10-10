package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = ReadFile.readHomeFile();
        // начинаем цикл обработки событий
        SensorEvent event = SensorEvent.getNextSensorEvent();
        EventDriver.run(event, smartHome);
    }
}

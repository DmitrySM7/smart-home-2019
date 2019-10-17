package ru.sbt.mipt.oop;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = FileReader.readHomeFile();
        // начинаем цикл обработки событий
        SensorEvent event = SensorEvent.getNextSensorEvent();
        new StateHandler(event, smartHome).stateHandle();
    }
}

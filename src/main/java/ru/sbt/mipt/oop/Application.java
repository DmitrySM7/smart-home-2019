package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = FileReader.readHomeFile();
        // начинаем цикл обработки событий
        Alarm alarm = new Alarm("1");
        Collection<EventHandler> eventHandlers = new ArrayList<>();
        eventHandlers.add(new LightSolution(smartHome));
        eventHandlers.add(new DoorSolution(smartHome));
        eventHandlers.add(new HallDoorSolution(smartHome));
        eventHandlers.add(new AlarmSolution(alarm));
        EventDecorator decorator = new EventDecorator(new EventProcessor(eventHandlers), alarm);
        new StateHandler(decorator).stateHandle();
    }
}
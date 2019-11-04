package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationState;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String... args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        StateHandler stateHandler = context.getBean(StateHandler.class);
        stateHandler.stateHandle();

        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        //StateHandler stateHandler = (StateHandler) ctx.getBean("stateHandler");
        // считываем состояние дома из файла
//        SmartHome smartHome = FileReader.readHomeFile();
//        // начинаем цикл обработки событий
//        SensorEvent event = new NextSensorEvent().getNextSensorEvent();
//        Alarm alarm = new Alarm(new DeactivationState(), "Qwerty123");
//        new StateHandler(event, smartHome, alarm).stateHandle();
    }
}
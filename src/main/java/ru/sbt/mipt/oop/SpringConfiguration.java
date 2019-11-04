package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationState;

import java.io.IOException;

@Configuration
public class SpringConfiguration {
    @Bean
    SmartHome smartHome() throws IOException {
        return FileReader.readHomeFile();
    }

    @Bean
    Alarm alarm() {
        return new Alarm(new DeactivationState(),"1234");
    }

    @Bean
    SensorEvent sensorEvent() {
        return new NextSensorEvent().getNextSensorEvent();
    }
    @Bean
    StateHandler stateHandler() throws IOException {
        return new StateHandler(sensorEvent(),smartHome(), alarm());
    }
}
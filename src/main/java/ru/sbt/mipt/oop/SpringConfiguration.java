package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Adapters.AdapterEventHandler;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationState;

import java.io.IOException;

@Configuration
public class SpringConfiguration {

    public SmartHome smartHome() throws IOException {
        return FileReader.readHomeFile();
    }


    public Alarm alarm() {
        return new Alarm(new DeactivationState(),"1234");
    }

    @Bean
    public StateHandler getStateHandler() throws IOException {
        return new AdapterEventHandler(smartHome(), alarm());
    }
}
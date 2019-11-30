package ru.sbt.mipt.oop;

import coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.adapter.*;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.FileReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
@Import(RC_ConfigurationFile.class)
public class SpringConfigurationFile {
    @Bean
    SensorEventsManager sensorEventsManager(EventHandler eventProcessor, CCSensorEventConverter ccSensorEventConverter){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AdapterEventHandler(eventProcessor, ccSensorEventConverter));
        return sensorEventsManager;
    }

    @Bean
    public EventHandler eventProcessor(Collection<EventHandler> collectionEventProcess) {
        return new EventDecorator(new EventProcessor(collectionEventProcess), alarm());
    }

    @Bean
    EventHandler lightSolution(){
        return new LightSolution(smartHome());
    }

    @Bean
    EventHandler hallDoorSolution(){
        return new HallDoorSolution(smartHome());
    }

    @Bean
    EventHandler doorSolution(){
        return new DoorSolution(smartHome());
    }

    @Bean
    EventHandler alarmSolution(){
        return new AlarmSolution(alarm());
    }

    @Bean
    SmartHome smartHome() {
        return new FileReader().readHomeFile();

    }

    @Bean
    Alarm alarm() {
        return new Alarm("1234");
    }


    @Bean
    public CCSensorEventConverter ccSensorEventConverter(LightsIsOffConverter lightsIsOffConverter) {
        return new LightsIsOnConverter(lightsIsOffConverter);
    }

    @Bean
    public LightsIsOffConverter lightsIsOffConverter(DoorIsOpenedConverter doorIsOpenedConverter) {
        return new LightsIsOffConverter(doorIsOpenedConverter);
    }

    @Bean
    public DoorIsOpenedConverter doorIsOpenedConverter(DoorIsClosedConverter doorIsClosedConverter) {
        return new DoorIsOpenedConverter(doorIsClosedConverter);
    }

    @Bean
    DoorIsClosedConverter doorIsClosedConverter() {
        return new DoorIsClosedConverter(null);
    }
}

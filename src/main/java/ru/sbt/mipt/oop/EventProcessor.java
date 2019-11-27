package ru.sbt.mipt.oop;

import java.util.Collection;

public class EventProcessor implements EventHandler{
    private Collection<EventHandler> eventHandlerCollection;

    public EventProcessor(Collection<EventHandler> eventHandlerCollection) {
        this.eventHandlerCollection = eventHandlerCollection;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        for(EventHandler eventProcessor: eventHandlerCollection){
            eventProcessor.handleEvent(event);
        }
    }
}

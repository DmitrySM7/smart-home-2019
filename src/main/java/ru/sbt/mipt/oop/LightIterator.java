package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LightIterator implements Iterator<Light>, ActionIterator<Light,Room> {

    private List<Room> roomArr;
    private int roomPosition = 0;
    private int lightPosition = 0;

    public LightIterator(SmartHome smartHome) {
        roomArr = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public Light next() {
        if (hasNext()) {
            List<Light> lightList = new ArrayList<>(roomArr.get(roomPosition).getLights());
            Light light = lightList.get(lightPosition);
            lightPosition++;
            return light;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (roomPosition < roomArr.size()) {
            if (lightPosition < roomArr.get(roomPosition).getLights().size()) {
                return true;
            } else {
                roomPosition++;
                lightPosition = 0;
                return hasNext();
            }
        }
        return false;
    }

    public Room getCurrentRoom() {
        return roomArr.get(roomPosition);
    }

    @Override
    public void handleFunction(BiFunction<Light, Room, Void> func) {
        while(hasNext()) {
            Light light = next();
            Room room = getCurrentRoom();
            func.apply(light, room);
        }
    }
}

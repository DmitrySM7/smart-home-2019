package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DoorIterator implements Iterator<Door>, ActionIterator<Door,Room> {

    private List<Room> roomArr;
    private int roomPosition = 0;
    private int doorPosition = 0;

    public DoorIterator(SmartHome smartHome) {
        roomArr = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public boolean hasNext() {
        if (roomPosition < roomArr.size()) {
            if (doorPosition < roomArr.get(roomPosition).getDoors().size()) {
                return true;
            } else {
                roomPosition++;
                doorPosition = 0;
                return hasNext();
            }
        }
        return false;
    }

    @Override
    public Door next() {
        if (hasNext()) {
            List<Door> doorList = new ArrayList<>(roomArr.get(roomPosition).getDoors());
            Door door = doorList.get(doorPosition);
            doorPosition++;
            return door;
        } else {
            return null;
        }
    }


    public Room getCurrentRoom() {
        if (roomPosition < roomArr.size()) return roomArr.get(roomPosition);
        else return null;
    }

    @Override
    public void handleFunction(BiFunction<Door, Room, Void> func) {
        while (hasNext()) {
            Door door = next();
            Room room = getCurrentRoom();
            func.apply(door,room);
        }
    }
}
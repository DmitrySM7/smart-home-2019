package ru.sbt.mipt.oop;

import java.util.function.BiFunction;

public class DeviceAction implements Action {

    private Object firstComponent;
    private Object secondComponent;
    private BiFunction<Object, Object, Void> function;

    public DeviceAction(BiFunction<Object, Object, Void> function) {
        this.function = function;
    }

    @Override
    public void act() {
        function.apply(firstComponent, secondComponent);
    }

    @Override
    public void updateComponent(Object object) {
        if (object instanceof Room) {
            this.secondComponent = object;
        }
        if (object instanceof Door || object instanceof Light) {
            this.firstComponent = object;
        }
    }
}

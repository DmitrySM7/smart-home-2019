package ru.sbt.mipt.oop;

import java.util.function.BiFunction;


public interface ActionIterator<T, E> {
    public void handleFunction(BiFunction<T, E, Void> func);
}
